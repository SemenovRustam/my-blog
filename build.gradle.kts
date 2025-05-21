plugins {
    id("java")
    id("war")
    kotlin("jvm")
}

group = "com.semenov"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework:spring-webmvc:6.1.5")
    implementation("org.springframework:spring-jdbc:6.1.5")
    implementation("org.springframework:spring-tx:6.1.5")

    implementation("org.springframework:spring-beans:6.1.5")
    implementation("org.springframework.data:spring-data-jdbc:3.5.0")
    implementation("org.thymeleaf:thymeleaf-spring6:3.1.2.RELEASE")

    implementation("org.springframework:spring-webmvc:6.1.5")
    implementation ("org.postgresql:postgresql:42.7.3")
    implementation("org.mapstruct:mapstruct-processor:1.6.3")
    implementation("org.mapstruct:mapstruct:1.6.3")
    annotationProcessor("org.mapstruct:mapstruct-processor:1.6.3")
    annotationProcessor("org.projectlombok:lombok-mapstruct-binding:0.2.0")
    implementation("commons-io:commons-io:2.6")

    compileOnly("jakarta.servlet:jakarta.servlet-api:6.0.0")
    compileOnly("org.projectlombok:lombok:1.18.30")
    annotationProcessor("org.projectlombok:lombok:1.18.30")

    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.springframework:spring-test:5.3.23")
    implementation("org.springframework:spring-context:5.3.23")


    testImplementation("org.junit.jupiter:junit-jupiter:5.9.3")
    testImplementation("org.mockito:mockito-core:5.3.1")
    testImplementation("org.mockito:mockito-junit-jupiter:5.3.1")
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<War> {
    archiveFileName.set("my-blog.war")
}
kotlin {
    jvmToolchain(21)
}