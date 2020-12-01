plugins {
    application
    id("org.springframework.boot").version("2.4.0")
    id("io.spring.dependency-management").version("1.0.10.RELEASE")
}

val mapstructVersion = "1.4.1.Final";
val junitVersion = "5.7.0"
val mockitoVersion = "3.6.28"

application {
    mainClass.set("pl.training.tdd.Application")
}

tasks.compileJava {
    options.release.set(15)
}

tasks.test {
    useJUnitPlatform()
}

repositories {
    jcenter()
    mavenCentral()
}

dependencies {
    //implementation("org.springframework.boot:spring-boot-starter-web")
    //implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    //runtimeOnly("com.h2database:h2")
    //compileOnly("org.projectlombok:lombok")
    //annotationProcessor("org.projectlombok:lombok")
    //implementation("org.mapstruct:mapstruct:$mapstructVersion")
    //annotationProcessor("org.mapstruct:mapstruct-processor:$mapstructVersion")

    //testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.junit.jupiter:junit-jupiter-api:$junitVersion")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:$junitVersion")
    testImplementation("org.junit.jupiter:junit-jupiter-params:$junitVersion")
    testImplementation("org.mockito:mockito-core:$mockitoVersion")
}
