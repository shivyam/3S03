import { expect, test } from "@playwright/test";

test.describe("Checkbox Elements", () => {
  // load checkbox element page
  test.beforeEach(async ({ page }) => {
    await page.goto("https://the-internet.herokuapp.com/checkboxes");
  });

  test("checks if checkbox form with 2 checkbox inputs exists on loaded page", async ({ page }) => {
    const form = page.locator("#checkboxes");
    await expect(form).toHaveCount(1);

    await expect(form.locator('input[type="checkbox"]').first()).toBeVisible();
    await expect(form.locator('input[type="checkbox"]').nth(1)).toBeVisible();
  });

    test("checks if first checkbox is unchecked and second checkbox is checked on the loaded page", async ({ page }) => {
    const firstCheckbox = page.locator("#checkboxes input[type='checkbox']").first();
    const secondCheckbox = page.locator("#checkboxes input[type='checkbox']").nth(1);

    await expect(firstCheckbox).not.toBeChecked();
    await expect(secondCheckbox).toBeChecked();
  });

  test("checks if first checkbox can be checked and second checkbox can be unchecked", async ({ page }) => {
    const firstCheckbox = page.locator("#checkboxes input[type='checkbox']").first();
    const secondCheckbox = page.locator("#checkboxes input[type='checkbox']").nth(1);

    await firstCheckbox.check();
    await expect(firstCheckbox).toBeChecked();

    await secondCheckbox.uncheck();
    await expect(secondCheckbox).not.toBeChecked();
  });

    test("checks if both checkboxes can be checked at the same time", async ({ page }) => {
    const firstCheckbox = page.locator("#checkboxes input[type='checkbox']").first();
    const secondCheckbox = page.locator("#checkboxes input[type='checkbox']").nth(1);

    await firstCheckbox.check();
    await secondCheckbox.check();

    await expect(firstCheckbox).toBeChecked();
    await expect(secondCheckbox).toBeChecked();
  });

  test("checks if both checkboxes can be unchecked at the same time", async ({ page }) => {
    const firstCheckbox = page.locator("#checkboxes input[type='checkbox']").first();
    const secondCheckbox = page.locator("#checkboxes input[type='checkbox']").nth(1);

    await firstCheckbox.uncheck();
    await secondCheckbox.uncheck();

    await expect(firstCheckbox).not.toBeChecked();
    await expect(secondCheckbox).not.toBeChecked();
  });

});
