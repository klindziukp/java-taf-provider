<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
  xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
  <modelVersion>4.0.0</modelVersion>
  <groupId>[=generationData.groupId]</groupId>
  <artifactId>[=generationData.artifactId]</artifactId>
  <version>0.0.1-SNAPSHOT</version>
  <name>[=generationData.projectName]</name>
  <description>[=generationData.description]</description>

  <properties>
    <java.version>[=generationData.javaVersion]</java.version>
    <maven.compiler.version>[=generationData.generationConfig.mavenConfig.compilerVersion]</maven.compiler.version>
    <maven.compiler.source>[=generationData.javaVersion]</maven.compiler.source>
    <maven.compiler.target>[=generationData.javaVersion]</maven.compiler.target>
    <maven.surefire.plugin.version>[=generationData.generationConfig.mavenConfig.surefireVersion]</maven.surefire.plugin.version>
    <maven.surefire.aspectj.version>[=generationData.generationConfig.mavenConfig.aspectjVersion]</maven.surefire.aspectj.version>
    <#list generationData.moduleItems as moduleItem>
    <[=moduleItem.artifactId].version>[=moduleItem.version]</[=moduleItem.artifactId].version>
    </#list>
  </properties>

  <repositories>
    <repository>
      <id>klindziuk-central</id>
      <name>artifactory.klindziuk.com-releases</name>
      <url>https://artifactory.klindziuk.com/artifactory/KLINDZIUK-MAVEN</url>
    </repository>
    <repository>
      <id>klindziuk-snapshots</id>
      <name>artifactory.klindziuk.com-snapshots</name>
      <url>https://artifactory.klindziuk.com/artifactory/KLINDZIUK-MAVEN</url>
    </repository>
  </repositories>

  <dependencies>
  <#list generationData.moduleItems as moduleItem>
  <dependency>
    <groupId>[=moduleItem.groupId]</groupId>
    <artifactId>[=moduleItem.artifactId]</artifactId>
    <version>${[=moduleItem.artifactId].version}</version>
  </dependency>
  </#list>
  </dependencies>

  <build>
    <plugins>
      <plugin>
        <groupId>org.apache.maven.plugins</groupId>
        <artifactId>maven-compiler-plugin</artifactId>
        <version>${maven.compiler.version}</version>
        <configuration>
          <source>${maven.compiler.source}</source>
          <target>${maven.compiler.target}</target>
        </configuration>
      </plugin>
      <plugin>
        <groupId>org.apache.maven.plugins</groupId>
        <artifactId>maven-surefire-plugin</artifactId>
        <version>${maven.surefire.plugin.version}</version>
        <configuration>
          <argLine>
            -javaagent:"${settings.localRepository}/org/aspectj/aspectjweaver/${maven.surefire.aspectj.version}/aspectjweaver-${maven.surefire.aspectj.version}.jar"
          </argLine>
        </configuration>
        <dependencies>
          <dependency>
            <groupId>org.aspectj</groupId>
            <artifactId>aspectjweaver</artifactId>
            <version>${maven.surefire.aspectj.version}</version>
          </dependency>
        </dependencies>
      </plugin>
    </plugins>
  </build>

</project>