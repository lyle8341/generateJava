package com.yetu.plugin

import org.gradle.api.Plugin
import org.gradle.api.Project

class CreateJavaPlugin implements Plugin<Project> {
    @Override
    void apply(Project project) {
        project.getTasks().create("convert2war",CreateJavaTask.class)

        //execute回调会在Configure project阶段运行的
        /*project.getTasks().create("convert2war", CreateJavaTask.class, new Action<CreateJavaTask>() {
            @Override
            void execute(CreateJavaTask task) {
                task.packageName = "once"
                task.doTask()
                task.packageName = "twice"
            }
        })*/
    }
}