plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    `maven-publish`
}

android {
    namespace = "com.example.supercalc"
    compileSdk = 34

    defaultConfig {
        minSdk = 24

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}



publishing {

    // Define the repository the package will be published to
    repositories {
        maven {
            name = "GitHubPackages"
            url = uri("https://maven.pkg.github.com/red-dragon65/LibraryTestApplication")
            credentials {
                username = project.findProperty("gpr.user") as String? ?: System.getenv("USERNAME")
                password = project.findProperty("gpr.key") as String? ?: System.getenv("TOKEN")
            }
        }
    }

    // Define the package you want to publish
    publications {
        register<MavenPublication>("release") {

            groupId = "com.cyberllama"
            artifactId = "super-calc"
            version = "0.1.0"

            // This won't work, use "artifact" instead
            //from(components["java"])

            // You can manually load the artifact if needed
            //artifact("${layout.buildDirectory}/outputs/arr/SuperCalc-release.arr")

            // Android docs recommended doing this
            afterEvaluate{
                from(components["release"])
            }
        }
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}