package com.gradle.test

import com.android.build.api.transform.Context
import com.android.build.api.transform.DirectoryInput
import com.android.build.api.transform.JarInput;
import com.android.build.api.transform.QualifiedContent;
import com.android.build.api.transform.Transform
import com.android.build.api.transform.TransformException
import com.android.build.api.transform.TransformInput
import com.android.build.api.transform.TransformOutputProvider
import com.android.build.gradle.internal.pipeline.TransformManager

import java.util.Set;

/**
 * Create by wangchao on 2018/12/12 14:06
 */
public class RegeisterTransform extends Transform {
    @Override
    public String getName() {
        return "test_plugin";
    }

    @Override
    public Set<QualifiedContent.ContentType> getInputTypes() {
        return TransformManager.CONTENT_CLASS;
    }

    @Override
    public Set<? super QualifiedContent.Scope> getScopes() {
        return TransformManager.SCOPE_FULL_PROJECT;
    }

    @Override
    public boolean isIncremental() {
        return true;
    }

    @Override
    void transform(Context context, Collection<TransformInput> inputs, Collection<TransformInput> referencedInputs, TransformOutputProvider outputProvider, boolean isIncremental) throws IOException, TransformException, InterruptedException {
        super.transform(context, inputs, referencedInputs, outputProvider, isIncremental)

        // 遍历输入文件
        inputs.each { TransformInput input ->
            // 遍历jar
            input.jarInputs.each { JarInput jarInput ->
                if (jarInput.status != Status.NOTCHANGED && cacheMap) {
                    cacheMap.remove(jarInput.file.absolutePath)
                }
                scanJar(jarInput, outputProvider, scanProcessor)
            }
            // 遍历目录
            input.directoryInputs.each { DirectoryInput directoryInput ->
                long dirTime = System.currentTimeMillis();
                // 获得产物的目录
                File dest = outputProvider.getContentLocation(directoryInput.name, directoryInput.contentTypes, directoryInput.scopes, Format.DIRECTORY)
                String root = directoryInput.file.absolutePath
                if (!root.endsWith(File.separator))
                    root += File.separator
                //遍历目录下的每个文件
//                directoryInput.file.eachFileRecurse { File file ->
//                    def path = file.absolutePath.replace(root, '')
//                    if (file.isFile()) {
//                        def entryName = path
//                        if (!leftSlash) {
//                            entryName = entryName.replaceAll("\\\\", "/")
//                        }
//                        scanProcessor.checkInitClass(entryName, new File(dest.absolutePath + File.separator + path))
//                        if (scanProcessor.shouldProcessClass(entryName)) {
//                            scanProcessor.scanClass(file)
//                        }
//                    }
//                }
//                long scanTime = System.currentTimeMillis();
//                // 处理完后拷到目标文件
//                FileUtils.copyDirectory(directoryInput.file, dest)
//                println "auto-register cost time: ${System.currentTimeMillis() - dirTime}, scan time: ${scanTime - dirTime}. path=${root}"
            }
        }
    }

    void scanJar(JarInput jarInput, TransformOutputProvider outputProvider) {

        // 获得输入文件
        File src = jarInput.file
        //遍历jar的字节码类文件，找到需要自动注册的类
        File dest = getDestFile(jarInput, outputProvider)
        long time = System.currentTimeMillis();
    }

}
