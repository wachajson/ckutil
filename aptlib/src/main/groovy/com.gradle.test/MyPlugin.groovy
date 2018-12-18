package com.gradle.test

import com.android.build.api.transform.Transform
import org.gradle.api.Plugin
import org.gradle.api.Project
import com.android.build.gradle.AppPlugin
import com.android.build.gradle.AppExtension
public class MyPlugin implements Plugin<Project>{

    public static final String  EXT_NAME = "my_plugin";

    @Override
    void apply(Project project) {

        //自定义配置
        project.extensions.create(EXT_NAME,AutoRegisterConfig)

        //是否是app
        def isApp = project.plugins.hasPlugin(AppPlugin)

        project.task("testTask"){
            println("isApp = " + isApp)
        }
        if(isApp){
            // 通过AppExtension得到build.gradle里的配置和任务信息
            def android = project.extensions.getByType(AppExtension)
            project.gradle.addListener(new TimeListener())
            def  transformImpl = new RegeisterTransform();

            android.registerTransform(transformImpl)

            project.afterEvaluate {
                init(project,transformImpl)
            }

        }

    }


    public void init(Project project ,RegeisterTransform transform){
        AutoRegisterConfig config = project.extensions.findByName(EXT_NAME) as AutoRegisterConfig
    }
}