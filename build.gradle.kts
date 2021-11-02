import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version "2.4.12"
    id("io.spring.dependency-management") version "1.0.11.RELEASE"
    kotlin("jvm") version "1.4.32"
    kotlin("plugin.spring") version "1.4.32"
    kotlin("plugin.jpa") version "1.4.32"
    kotlin("kapt") version "1.3.72"
}

group = "gr.ether"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_11

repositories {
    mavenCentral()
}

extra["springBootAdminVersion"] = "2.4.3"

dependencies {


//    annotationProcessor("org.mapstruct:mapstruct-processor:1.4.2.Final")
//    implementation("org.mapstruct:mapstruct:1.5.0.Beta1")
//    kapt("org.mapstruct:mapstruct-processor:1.5.0.Beta1")


    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-actuator")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-validation")

    implementation("de.codecentric:spring-boot-admin-starter-client")
    implementation("org.springframework.boot:spring-boot-starter-webflux")

    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation("org.postgresql:postgresql:42.3.1")

    implementation("org.mapstruct:mapstruct:1.4.2.Final")
    kapt("org.mapstruct:mapstruct-processor:1.4.2.Final")

    testImplementation("org.springframework.boot:spring-boot-starter-test")


}

//kapt {
//    arguments {
//        // Set Mapstruct Configuration options here
//        // https://kotlinlang.org/docs/reference/kapt.html#annotation-processor-arguments
//        // https://mapstruct.org/documentation/stable/reference/html/#configuration-options
//        // arg("mapstruct.defaultComponentModel", "spring")
//    }
//}
//sourceSets {
//    named("main") {
//        java.srcDir("build/generated/source/apt/main")
//    }
//}
//sourceSets {
//    main.java.srcDirs += "build/generated/source/apt/main"
//}

dependencyManagement {
    imports {
        mavenBom("de.codecentric:spring-boot-admin-dependencies:${property("springBootAdminVersion")}")
    }
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "11"
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}
