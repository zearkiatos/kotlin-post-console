run:
	./gradlew run --console=plain

package:
	./gradlew distTar

build:
	./gradlew --no-daemon shadowJar

test:
	./gradlew test