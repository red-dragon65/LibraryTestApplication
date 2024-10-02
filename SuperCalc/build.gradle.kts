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


// THIS PUBLISHES THE MODULE LOCALLY FOR TESTING PURPOSES
// Stores the file under ~/.m2/repository/com/cyberllama/super-calc
publishing {
    publications {
        register<MavenPublication>("release") {
            groupId = "com.cyberllama"
            artifactId = "super-calc"
            version = "0.1.0"

            afterEvaluate {
                from(components["release"])
            }
        }
    }

    // THIS WILL PUBLISH LOCALLY FOR TESTING PURPOSES
    repositories {
        maven {
            name = "myrepo"
            url = uri(layout.buildDirectory.dir("repo"))
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