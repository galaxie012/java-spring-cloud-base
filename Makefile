docker:
	./gradlew clean build
	docker build -t entryimage:v2 . -f Dockerfile.test

.PHONY: docker
