The changes in this branch:

- Enabled AndroidX and Jetifier in gradle.properties
- Disabled Gradle configuration cache for CI stability
- Will update Compose-based UI: make bottom navigation icons-only and fix long title wrapping in composables (next commit)

Files changed:
- gradle.properties

Notes:
After this commit, please re-run CI to ensure the Gradle configuration error is resolved. If CI still fails, I'll inspect AGP/Gradle versions next.
