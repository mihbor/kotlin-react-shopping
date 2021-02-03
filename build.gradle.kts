import org.jetbrains.kotlin.gradle.targets.js.webpack.KotlinWebpack

plugins {
  kotlin("multiplatform") version "1.4.10"
  application
}
group = "me.user"
version = "1.0-SNAPSHOT"

repositories {
  mavenCentral()
  jcenter()
  maven {
    url = uri("https://dl.bintray.com/kotlin/ktor")
  }
  maven {
    url = uri("https://dl.bintray.com/kotlin/kotlinx")
  }
  maven {
    url = uri("https://dl.bintray.com/kotlin/kotlin-js-wrappers")
  }
}
kotlin {
  jvm {
    compilations.all {
      kotlinOptions.jvmTarget = "1.8"
    }
    withJava()
  }
  js {
    browser {
      binaries.executable()
      webpackTask {
        cssSupport.enabled = true
      }
      runTask {
        cssSupport.enabled = true
      }
      testTask {
        useKarma {
          useChromeHeadless()
          webpackConfig.cssSupport.enabled = true
        }
      }
    }
  }
  sourceSets {
    val commonMain by getting
    val commonTest by getting {
      dependencies {
        implementation(kotlin("test-common"))
        implementation(kotlin("test-annotations-common"))
      }
    }
    val jvmMain by getting {
      dependencies {
        implementation("io.ktor:ktor-server-netty:1.4.0")
        implementation("io.ktor:ktor-html-builder:1.4.0")
        implementation("org.jetbrains.kotlinx:kotlinx-html-jvm:0.7.2")
      }
    }
    val jvmTest by getting {
      dependencies {
        implementation(kotlin("test-junit"))
      }
    }
    val jsMain by getting {
      dependencies {
        implementation("org.jetbrains.kotlinx:kotlinx-html-js:0.7.2")
        implementation("org.jetbrains:kotlin-react:17.0.1-pre.144-kotlin-1.4.21")
        implementation("org.jetbrains:kotlin-react-dom:17.0.1-pre.144-kotlin-1.4.21")
        implementation("org.jetbrains:kotlin-react-router-dom:5.2.0-pre.144-kotlin-1.4.21")
        implementation("org.jetbrains:kotlin-styled:5.2.0-pre.144-kotlin-1.4.21")
      }
    }
    val jsTest by getting {
      dependencies {
        implementation(kotlin("test-js"))
      }
    }
  }
}
application {
  mainClassName = "ServerKt"
}
tasks.getByName<KotlinWebpack>("jsBrowserProductionWebpack") {
  outputFileName = "output.js"
}
tasks.getByName<Jar>("jvmJar") {
  dependsOn(tasks.getByName("jsBrowserProductionWebpack"))
  val jsBrowserProductionWebpack = tasks.getByName<KotlinWebpack>("jsBrowserProductionWebpack")
  from(File(jsBrowserProductionWebpack.destinationDirectory, jsBrowserProductionWebpack.outputFileName))
  from(File(jsBrowserProductionWebpack.destinationDirectory, jsBrowserProductionWebpack.outputFileName + ".map"))
  from(File(jsBrowserProductionWebpack.destinationDirectory, "styles.css"))
}
tasks.getByName<JavaExec>("run") {
  dependsOn(tasks.getByName<Jar>("jvmJar"))
  classpath(tasks.getByName<Jar>("jvmJar"))
}