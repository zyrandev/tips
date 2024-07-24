plugins {
    `java-library`
}

repositories {
    mavenCentral()
}

dependencies {
    api(project(":api"))

    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.test {
    useJUnitPlatform()
}