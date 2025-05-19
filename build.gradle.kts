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
    // Spring 6 (обязательно используем jakarta-совместимые версии)
    implementation("org.springframework:spring-webmvc:6.1.5")
    implementation("org.springframework:spring-jdbc:6.1.5")
    implementation("org.springframework:spring-tx:6.1.5")

    implementation("org.springframework:spring-beans:6.1.5")
    implementation("org.springframework.data:spring-data-jdbc:3.5.0")
    implementation("org.thymeleaf:thymeleaf-spring6:3.1.2.RELEASE")

    // Spring 6+ (Jakarta-compatible)
    implementation("org.springframework:spring-webmvc:6.1.5")

    // Servlet API (ТОЛЬКО JAKARTA для Tomcat 10+)
    compileOnly("jakarta.servlet:jakarta.servlet-api:6.0.0")
    implementation ("org.postgresql:postgresql:42.7.3")


    // Lombok
    compileOnly("org.projectlombok:lombok:1.18.30")
    annotationProcessor("org.projectlombok:lombok:1.18.30")

    // JUnit
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    implementation(kotlin("stdlib-jdk8"))
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