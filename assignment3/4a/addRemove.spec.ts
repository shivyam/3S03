import { expect, test } from "@playwright/test";


test.describe("Add/Remove Elements", () => {
  // load add/remove element page
  test.beforeEach(async ({ page }) => {
    await page.goto("https://the-internet.herokuapp.com/add_remove_elements/");
  });

  test("check if Add Element button exists and no Delete button exists on page load", async ({
    page,
  }) => {
    await expect(
      page.getByRole("button", { name: "Add Element" }),
    ).toBeVisible();
    await expect(page.getByRole("button", { name: "Delete" })).toHaveCount(0);
  });

  test("adds one delete button to the loaded page", async ({ page }) => {
    await page.getByRole("button", { name: "Add Element" }).click();
    await expect(page.getByRole("button", { name: "Delete" })).toHaveCount(1);
  });

  test("adds 3 delete buttons to the loaded page", async ({ page }) => {
    const addButton = page.getByRole("button", { name: "Add Element" });
    await addButton.click();
    await addButton.click();
    await addButton.click();

    await expect(page.getByRole("button", { name: "Delete" })).toHaveCount(3);
  });

  test("removes a delete button immediately after adding one", async ({ page }) => {
    const addButton = page.getByRole("button", { name: "Add Element" });
    const deleteButtons = page.getByRole("button", { name: "Delete" });

    await addButton.click();
    await expect(deleteButtons).toHaveCount(1);

    await deleteButtons.first().click();
    await expect(deleteButtons).toHaveCount(0);
  });

  test("removes all buttons after adding multiple buttons ", async ({ page }) => {
    const addButton = page.getByRole("button", { name: "Add Element" });
    const deleteButtons = page.getByRole("button", { name: "Delete" });
    
    await addButton.click();
    await addButton.click();
    await addButton.click();
    await addButton.click();
 
    await expect(deleteButtons).toHaveCount(4);

    while ((await deleteButtons.count()) > 0) {
      await deleteButtons.first().click();
    }

    await expect(deleteButtons).toHaveCount(0);
  });
});