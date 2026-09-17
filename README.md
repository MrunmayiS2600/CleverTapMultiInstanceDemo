# CleverTap Multi-Instance Demo

Minimal Android (Kotlin) app demonstrating CleverTap's multi-instance SDK support,
sending data to two separate CleverTap accounts with different `onUserLogin` identity logic.

## Where the two projects are configured

- **Project 1** (default instance) — [AndroidManifest.xml](app/src/main/AndroidManifest.xml)
  via the `CLEVERTAP_ACCOUNT_ID` / `CLEVERTAP_TOKEN` meta-data tags.
  Replace `YOUR_PROJECT1_ACCOUNT_ID` / `YOUR_PROJECT1_ACCOUNT_TOKEN` with the real values.
- **Project 2** (additional instance) — [CleverTapMultiInstanceApp.kt](app/src/main/java/com/example/clevertapmultiinstance/CleverTapMultiInstanceApp.kt)
  via `PROJECT2_ACCOUNT_ID` / `PROJECT2_ACCOUNT_TOKEN`.
  Replace with a second, real CleverTap account's credentials.

Both are placeholders — I don't have CleverTap dashboard access from this session
(the CleverTap MCP connector needs to be authorized first), so I couldn't create or
look up real account IDs/tokens for either project.

## onUserLogin behavior

- Project 1 (`MainActivity.kt`): `Identity` = the phone number the user types in.
- Project 2 (`MainActivity.kt`): `Identity` = a random id generated per login (`user_xxxxxxxx`),
  unrelated to the phone number.

Both instances also fire a `Logged In` event right after `onUserLogin`, so you can see
activity land on both dashboards.

## Running it

1. Open this folder in Android Studio — it will generate the Gradle wrapper and sync automatically.
2. Fill in the four placeholder credential strings described above.
3. Run on a device/emulator, enter a phone number, tap the login button.
4. Check both CleverTap dashboards (Profiles / Events) for the respective `Identity` values.
