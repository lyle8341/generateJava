# 制作 gradle 插件

+ 控制台执行发布命令
  ```shell
  $ ./gradlew publish
  ```

+ 自定义gradle 插件的使用
  1. 在工程的根目录的 build.gradle 中添加库地址
     ```shell
      repositories {
        maven {
            //local maven repo path
            mavenLocal()
        }
      }
     buildscript {
        repositories {
            mavenLocal()
        }
        dependencies {
            //该classpath声明说明了在执行其余的build脚本时，class loader可以使用这些你提供的依赖项
            classpath 'com.yetu.plugin:generateJava:1.0.0'
        }
     }     
     ```
  2. 在 Module 的 build.gradle 中 apply 这个插件
     ```shell
     apply plugin: 'com.yetu.plugin'
     
     // 为插件传递参数
     convert2war {
       packageName = 'com.yetu.license'
       starterClazz = 'LicenseGenerateApplication.class'
     }
     
     convert2war.onlyIf {
       plugins.hasPlugin("war")
     }
     ```