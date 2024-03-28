# jdk 17
FROM openjdk:17-alpine

# 파일 저장 디렉토리
WORKDIR /app

# 빌드 된 jar 파일 복사
COPY build/libs/back-0.0.1-SNAPSHOT.jar /app/back.jar

# 파일 실행
ENTRYPOINT ["java", "-jar", "/app/back.jar"]