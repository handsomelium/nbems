def svn_url = "http://58.252.73.14:15088/svn/BEMS_BEMS_Product/AKE-BEMS-S/01_DvlpArea/03_Coding/01_Apps/Branches/nbems"

def svn_auth = "46cce315-8e6f-412a-a3e8-55d2fc923c92"

def tag = "latest"


node {
    //把选择的项目信息转为数组
    def selectedProjects = "${project_name}".split(',')

    stage('拉取代码') {

        checkout([$class: 'SubversionSCM', additionalCredentials: [], excludedCommitMessages: '',
        excludedRegions: '', excludedRevprop: '', excludedUsers: '', filterChangelog: false,
        ignoreDirPropChanges: false, includedRegions: '',
        locations: [[cancelProcessOnExternalsFail: true, credentialsId: "${svn_auth}",
        depthOption: 'infinity', ignoreExternalsOption: true, local: '.', remote: "${svn_url}"]],
        quietOperation: true, workspaceUpdater: [$class: 'UpdateUpdater']])

    }

    stage('编译打包，构建镜像') {

        for(int i=0;i<selectedProjects.size();i++){
            // 取出每个项目的名称和端口
            def currentProject = selectedProjects[i];
            // 项目名称
            def currentProjectName = currentProject.split('@')[0]
            // 项目启动端口
            def currentProjectPort = currentProject.split('@')[1]
            // 镜像名称
            def imageName = "${currentProjectName}:${tag}"
            // 若存在该镜像，则删除
            sh "/home/deploy.sh ${currentProjectName} ${tag} ${currentProjectPort}"

            if("${currentProjectName}" == "nbems-gateway" || "${currentProjectName}" == "nbems-auth"){

                sh "mvn -pl ${currentProjectName} -am -DskipTests clean package"
                // 开始构建镜像
                sh "docker build -f ${currentProjectName}/Dockerfile ./${currentProjectName} -t ${imageName}"

            }else{

                sh "mvn -pl nbems-modules/${currentProjectName} -am -DskipTests clean package"
                // 开始构建镜像
                sh "docker build -f nbems-modules/${currentProjectName}/Dockerfile ./nbems-modules/${currentProjectName} -t ${imageName}"

            }

        }

    }

    stage('部署服务') {

        for(int i=0;i<selectedProjects.size();i++){

            // 取出每个项目的名称和端口
            def currentProject = selectedProjects[i];
            // 项目名称
            def currentProjectName = currentProject.split('@')[0]
            // 项目启动端口
            def currentProjectPort = currentProject.split('@')[1]
            // 镜像名称
            def imageName = "${currentProjectName}:${tag}"

            sh "docker run -d -p ${currentProjectPort}:${currentProjectPort} -v /data/logs:/data/logs ${imageName}"

            echo '项目部署'

        }

    }
}



