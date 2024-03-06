plugins {
  id("com.diffplug.spotless")
}

spotless {
  kotlin {
    target("**/*.kt", "**/*.kts")
    targetExclude("**/build/**/*.*")
    ktlint().setUseExperimental(true).editorConfigOverride(['indent_size': '2', 'continuation_indent_size': '2'])
    licenseHeaderFile "$rootDir/spotless/copyright.kt"
    trimTrailingWhitespace()
    endWithNewline()
  }
}
