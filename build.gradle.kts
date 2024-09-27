val ktor_version: String by project
val kotlin_version: String by project
val logback_version: String by project
val ktlint by configurations.creating
val ktlint_version: String by project

plugins {
  application
  kotlin("jvm") version "1.5.31"
  id("org.jetbrains.kotlin.plugin.serialization") version "1.9.25"
}

group = "no.capjakt"
version = "0.0.1"
application {
  mainClass.set("no.capjakt.ApplicationKt")
}

repositories {
  mavenCentral()
}

// Set both Java and Kotlin to target jvm 11
java {
  sourceCompatibility = JavaVersion.VERSION_11
  targetCompatibility = JavaVersion.VERSION_11
}

tasks.create("stage") {
  dependsOn("installDist")
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
  kotlinOptions {
    jvmTarget = "11"
  }
}

dependencies {
  implementation("io.ktor:ktor-server-core:$ktor_version")
  implementation("io.ktor:ktor-serialization:$ktor_version")
  implementation("io.ktor:ktor-server-netty:$ktor_version")
  implementation("ch.qos.logback:logback-classic:$logback_version")

  // Ktlint
  ktlint("com.pinterest:ktlint:$ktlint_version") {
    attributes {
      attribute(Bundling.BUNDLING_ATTRIBUTE, objects.named(Bundling.EXTERNAL))
    }
  }
  testImplementation("io.ktor:ktor-server-tests:$ktor_version")
  testImplementation("org.jetbrains.kotlin:kotlin-test-junit:$kotlin_version")
}

val ktlintCheck by tasks.creating(JavaExec::class) {
  val outputDir = "${project.buildDir}/reports/ktlint/"
  val inputFiles = project.fileTree(mapOf("dir" to "src", "include" to "**/*.kt"))
  inputs.files(inputFiles)
  outputs.dir(outputDir)

  description = "Check Kotlin code style."
  classpath = ktlint
  main = "com.pinterest.ktlint.Main"
  args = listOf("src/**/*.kt")
}

val ktlintFormat by tasks.creating(JavaExec::class) {
  val outputDir = "${project.buildDir}/reports/ktlint/"
  val inputFiles = project.fileTree(mapOf("dir" to "src", "include" to "**/*.kt"))
  inputs.files(inputFiles)
  outputs.dir(outputDir)

  description = "Fix Kotlin code style deviations."
  classpath = ktlint
  main = "com.pinterest.ktlint.Main"
  args = listOf("-F", "src/**/*.kt")
}
