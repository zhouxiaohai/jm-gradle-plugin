## JM服务构建插件

### jm-Builder
> 服务打包插件

#### 插件导入

```
buildscript {
    repositories {
        maven{ url 'http://maven.ejiahe.com:8081/repository/gzb_public/'}
    }
    dependencies {
        classpath 'com.ejiahe.plugins:jm-builder:1.0.0'
    }
}
apply plugin: "jm.builder"
```
#### 插件功能说明
* jm dist -- 打包组
  |
  - gzbArtifact -- 打包所需文件
  |
  - gzbCopy -- 复制所有文件
  |
  - gzbDelete -- 删除上次任务
  |
  - gzbDisTar -- 打包
  |
  - gzbDistTemp -- 打包成临时对象
  |
  - publish2FTP -- 上传FTP
  |
  - updateMetaProperties -- 修改Meta文件
  
    
--------------------------------------------------------------------------
### jm-optional
> gradle不依赖插件

#### 插件导入
```
buildscript {
    repositories {
        maven{ url 'http://maven.ejiahe.com:8081/repository/gzb_public/'}
    }
    dependencies {
        classpath 'com.ejiahe.plugins:jm-optional:1.0.0'
    }
}

apply plugin: "jm.optional"
```
--------------------------------------------------------------------------
### jm-structure
> 服务构建插件

#### 插件导入
```
buildscript {
    repositories {
        maven{ url 'http://maven.ejiahe.com:8081/repository/gzb_public/'}
    }
    dependencies {
        classpath 'com.ejiahe.plugins:jm-structure:1.0.0'
    }
}

apply plugin: "jm.structure"
```

#### 插件功能说明
* jm structure -- 构建组
  |
  - structureAll -- 构建所有
  |
  - structureBinFile -- 构建bin目录下文件
  |
  - structureConfigFile -- 构建config目录下文件
  |
  - structureInstallFile -- 构建install目录下文件
  | 
  - structureStaticFrontend -- 构建static-frontend文件夹
  |
  - structureIgnore -- 构建ignore忽略文件
  |
  - structureChangeLog -- 构建changLog.html文件
  |
  - structureGradleProperties -- 构建gradle.properties配置文件  
