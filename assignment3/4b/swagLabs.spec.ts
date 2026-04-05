import { expect, test } from "@playwright/test";

const BASE_URL = "https://www.saucedemo.com";
const USERNAME = "standard_user";
const PASSWORD = "secret_sauce";

// Steps in the User Journey Outlined

test.describe("Saucedemo User Journey", () => {

  // Step 1 & 2: User opens the website and logs in
  test("Step 1-2: User opens website and logs in successfully", async ({ page }) => {
    await page.goto(BASE_URL);
    await expect(page).toHaveTitle("Swag Labs");
    await expect(page.locator("#user-name")).toBeVisible();
    await expect(page.locator("#password")).toBeVisible();

    // Log in
    await page.locator("#user-name").fill(USERNAME);
    await page.locator("#password").fill(PASSWORD);
    await page.locator("#login-button").click();

    // Redirect to products page
    await expect(page).toHaveURL(`${BASE_URL}/inventory.html`);
    await expect(page.locator(".title")).toHaveText("Products");
  });

  // Step 3: User views products
  test("Step 3: User views product listing", async ({ page }) => {
    await page.goto(BASE_URL);
    await page.locator("#user-name").fill(USERNAME);
    await page.locator("#password").fill(PASSWORD);
    await page.locator("#login-button").click();

    // Assert products are listed
    const items = page.locator(".inventory_item");
    await expect(items).toHaveCount(6);

    // Assert each product has a name, description, and price
    for (let i = 0; i < 6; i++) {
      await expect(items.nth(i).locator(".inventory_item_name")).toBeVisible();
      await expect(items.nth(i).locator(".inventory_item_desc")).toBeVisible();
      await expect(items.nth(i).locator(".inventory_item_price")).toBeVisible();
    }
  });

  // Step 4: User selects a product (views detail page)
  test("Step 4: User selects a product and views its detail page", async ({ page }) => {
    await page.goto(BASE_URL);
    await page.locator("#user-name").fill(USERNAME);
    await page.locator("#password").fill(PASSWORD);
    await page.locator("#login-button").click();

    // Click the first product name
    const firstProduct = page.locator(".inventory_item_name").first();
    const productName = await firstProduct.textContent();
    await firstProduct.click();

    // Assert we are on the detail page
    await expect(page).toHaveURL(/\/inventory-item\.html/);
    await expect(page.locator(".inventory_details_name")).toHaveText(productName!);
    await expect(page.locator(".inventory_details_price")).toBeVisible();
    await expect(page.locator(".inventory_details_desc")).toBeVisible();
  });

  // Step 5: User adds a product to cart
  test("Step 5: User adds a product to cart", async ({ page }) => {
    await page.goto(BASE_URL);
    await page.locator("#user-name").fill(USERNAME);
    await page.locator("#password").fill(PASSWORD);
    await page.locator("#login-button").click();

    // Add first product to cart from inventory page
    await page.locator(".inventory_item").first().locator("button").click();

    // Assert cart badge shows 1 item
    await expect(page.locator(".shopping_cart_badge")).toHaveText("1");
  });

  // Step 6: User views cart
  test("Step 6: User views cart and product is present", async ({ page }) => {
    await page.goto(BASE_URL);
    await page.locator("#user-name").fill(USERNAME);
    await page.locator("#password").fill(PASSWORD);
    await page.locator("#login-button").click();

    // Add first product to cart
    const firstProductName = await page.locator(".inventory_item_name").first().textContent();
    await page.locator(".inventory_item").first().locator("button").click();

    // Navigate to cart
    await page.locator(".shopping_cart_link").click();
    await expect(page).toHaveURL(`${BASE_URL}/cart.html`);

    // Assert the product appears in the cart
    await expect(page.locator(".cart_item")).toHaveCount(1);
    await expect(page.locator(".inventory_item_name")).toHaveText(firstProductName!);
  });

  // Step 7: User proceeds to checkout
  test("Step 7: User proceeds to checkout from cart", async ({ page }) => {
    await page.goto(BASE_URL);
    await page.locator("#user-name").fill(USERNAME);
    await page.locator("#password").fill(PASSWORD);
    await page.locator("#login-button").click();

    await page.locator(".inventory_item").first().locator("button").click();
    await page.locator(".shopping_cart_link").click();

    // Click checkout
    await page.locator("#checkout").click();
    await expect(page).toHaveURL(`${BASE_URL}/checkout-step-one.html`);
    await expect(page.locator(".title")).toHaveText("Checkout: Your Information");
  });

  // Step 8: User enters personal information
  test("Step 8: User enters personal information and continues", async ({ page }) => {
    await page.goto(BASE_URL);
    await page.locator("#user-name").fill(USERNAME);
    await page.locator("#password").fill(PASSWORD);
    await page.locator("#login-button").click();

    await page.locator(".inventory_item").first().locator("button").click();
    await page.locator(".shopping_cart_link").click();
    await page.locator("#checkout").click();

    // Fill in personal info
    await page.locator("#first-name").fill("Vanessa");
    await page.locator("#last-name").fill("Lai");
    await page.locator("#postal-code").fill("HEL LOO");
    await page.locator("#continue").click();

    // Assert we proceed to order summary
    await expect(page).toHaveURL(`${BASE_URL}/checkout-step-two.html`);
    await expect(page.locator(".title")).toHaveText("Checkout: Overview");
  });

  // Step 9: User confirms order
  test("Step 9: User confirms order and sees confirmation", async ({ page }) => {
    await page.goto(BASE_URL);
    await page.locator("#user-name").fill(USERNAME);
    await page.locator("#password").fill(PASSWORD);
    await page.locator("#login-button").click();

    await page.locator(".inventory_item").first().locator("button").click();
    await page.locator(".shopping_cart_link").click();
    await page.locator("#checkout").click();
    await page.locator("#first-name").fill("Vanessa");
    await page.locator("#last-name").fill("Lai");
    await page.locator("#postal-code").fill("HEL LOO");
    await page.locator("#continue").click();

    // Assert order summary shows item and total
    await expect(page.locator(".cart_item")).toHaveCount(1);
    await expect(page.locator(".summary_total_label")).toBeVisible();

    // Confirm order
    await page.locator("#finish").click();
    await expect(page).toHaveURL(`${BASE_URL}/checkout-complete.html`);
    await expect(page.locator(".complete-header")).toHaveText("Thank you for your order!");
  });

  // Step 10: User returns to product list page
  test("Step 10: User returns to product list after order", async ({ page }) => {
    await page.goto(BASE_URL);
    await page.locator("#user-name").fill(USERNAME);
    await page.locator("#password").fill(PASSWORD);
    await page.locator("#login-button").click();

    await page.locator(".inventory_item").first().locator("button").click();
    await page.locator(".shopping_cart_link").click();
    await page.locator("#checkout").click();
    await page.locator("#first-name").fill("Vanessa");
    await page.locator("#last-name").fill("Lai");
    await page.locator("#postal-code").fill("HEL LOO");
    await page.locator("#continue").click();
    await page.locator("#finish").click();

    // Click "Back Home"
    await page.locator("#back-to-products").click();
    await expect(page).toHaveURL(`${BASE_URL}/inventory.html`);
    await expect(page.locator(".title")).toHaveText("Products");

    // Assert cart is empty after completed order
    await expect(page.locator(".shopping_cart_badge")).not.toBeVisible();
  });

  // Step 11: User logs out
  test("Step 11: User logs out successfully", async ({ page }) => {
    await page.goto(BASE_URL);
    await page.locator("#user-name").fill(USERNAME);
    await page.locator("#password").fill(PASSWORD);
    await page.locator("#login-button").click();

    // log out
    await page.locator("#react-burger-menu-btn").click();
    await expect(page.locator(".bm-menu")).toBeVisible();
    await page.locator("#logout_sidebar_link").click();

    await expect(page).toHaveURL(BASE_URL + "/");
    await expect(page.locator("#login-button")).toBeVisible();
  });

  // Full end-to-end journey in one test
  test("Full user journey end-to-end", async ({ page }) => {
    // Step 1: Open website
    await page.goto(BASE_URL);
    await expect(page).toHaveTitle("Swag Labs");

    // Step 2: Log in
    await page.locator("#user-name").fill(USERNAME);
    await page.locator("#password").fill(PASSWORD);
    await page.locator("#login-button").click();
    await expect(page).toHaveURL(`${BASE_URL}/inventory.html`);

    // Step 3: View products
    await expect(page.locator(".inventory_item")).toHaveCount(6);

    // Step 4: Select a product
    const productName = await page.locator(".inventory_item_name").first().textContent();
    await page.locator(".inventory_item_name").first().click();
    await expect(page).toHaveURL(/\/inventory-item\.html/);
    await expect(page.locator(".inventory_details_name")).toHaveText(productName!);

    // Go back to inventory
    await page.locator("#back-to-products").click();

    // Step 5: Add product to cart
    await page.locator(".inventory_item").first().locator("button").click();
    await expect(page.locator(".shopping_cart_badge")).toHaveText("1");

    // Step 6: View cart
    await page.locator(".shopping_cart_link").click();
    await expect(page).toHaveURL(`${BASE_URL}/cart.html`);
    await expect(page.locator(".cart_item")).toHaveCount(1);

    // Step 7: Proceed to checkout
    await page.locator("#checkout").click();
    await expect(page).toHaveURL(`${BASE_URL}/checkout-step-one.html`);

    // Step 8: Enter personal information
    await page.locator("#first-name").fill("Vanessa");
    await page.locator("#last-name").fill("Lai");
    await page.locator("#postal-code").fill("HEL LOO");
    await page.locator("#continue").click();
    await expect(page).toHaveURL(`${BASE_URL}/checkout-step-two.html`);

    // Step 9: Confirm order
    await expect(page.locator(".cart_item")).toHaveCount(1);
    await page.locator("#finish").click();
    await expect(page).toHaveURL(`${BASE_URL}/checkout-complete.html`);
    await expect(page.locator(".complete-header")).toHaveText("Thank you for your order!");

    // Step 10: Return to product list
    await page.locator("#back-to-products").click();
    await expect(page).toHaveURL(`${BASE_URL}/inventory.html`);
    await expect(page.locator(".shopping_cart_badge")).not.toBeVisible();

    // Step 11: Log out
    await page.locator("#react-burger-menu-btn").click();
    await page.locator("#logout_sidebar_link").click();
    await expect(page).toHaveURL(BASE_URL + "/");
    await expect(page.locator("#login-button")).toBeVisible();
  });

});