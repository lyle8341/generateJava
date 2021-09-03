package com.yetu.plugin

import com.squareup.javapoet.JavaFile
import com.squareup.javapoet.MethodSpec
import com.squareup.javapoet.TypeSpec
import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer
import org.springframework.boot.builder.SpringApplicationBuilder

import javax.lang.model.element.Modifier

class CreateJavaTask extends DefaultTask {

    /**
     * 启动类报名
     */
    public String packageName;
    /**
     * 启动类名.class
     */
    public String starterClazz;

    CreateJavaTask() {
        description = 'convert jar to war'
        group = 'yetu'
    }

    @TaskAction
    void doTask() {
        MethodSpec main = MethodSpec.methodBuilder("configure")
                .addAnnotation(Override.class)
                .addModifiers(Modifier.PROTECTED)
                .returns(SpringApplicationBuilder.class)
                .addParameter(SpringApplicationBuilder.class, "application")
                .addStatement("return application.sources(" + starterClazz + ")")
                .build()

        //生成java类
        TypeSpec.Builder builder = TypeSpec.classBuilder("ServletInitializer")
                .addModifiers(Modifier.PUBLIC)
                .superclass(SpringBootServletInitializer.class).addMethod(main)
        JavaFile javaFile = JavaFile.builder(packageName, builder.build()).build()
        //将java写入到文件夹下
        File file = new File(project.projectDir, "src/main/java")
        if (!file.exists()) {
            file.mkdirs()
        }
        javaFile.writeTo(file)
        println "[write to]: ${file.absolutePath}"
    }
}