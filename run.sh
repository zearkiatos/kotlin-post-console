function run() {
    ./gradlew run
}

function package() {
    ./gradlew distTar
}

function build() {
    ./gradlew --no-daemon shadowJar
}

function test() {
    ./gradlew test
}