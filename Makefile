.PHONY: build test lint clean

build:
	mvn -B clean package -DskipTests

test:
	mvn -B test

lint:
	mvn -B checkstyle:check spotbugs:check || true

clean:
	mvn -B clean
