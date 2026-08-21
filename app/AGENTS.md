# Project Instructions & CI/CD Workflow Rules

## GitHub Actions CI Workflow (.github/workflows/android.yml)
The project utilizes an automated GitHub Actions CI runner located at `.github/workflows/android.yml`.
- **JDK & Gradle**: Uses JDK 17 (temurin) and Gradle 8.9.
- **Keystore**: Pre-generates `~/.android/debug.keystore` and copies to `./debug.keystore`.
- **Build**: Runs `assembleDebug`.
- **Signing**: Signs the compiled APK with `jarsigner` using `debug.keystore`.
- **Artifact Export**: Copies the signed APK to `build-apks/InsideMe.apk` and uploads it via `actions/upload-artifact@v4` under artifact name `InsideMe`.
