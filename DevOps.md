# DevOps

## Q:
需要测试人员完成一个DevOps文档，该文档不需要详细的步骤；但是最好有流程图，实施思路和架构设计。

## A:
### 原则:
统一的工具链、持续交付、合作

### 工具：
- Gitlab
- Harbor
- CMDB

### 流程:
> CI(持续集成)->CD(持续交付)->CD(持续部署)

- CI（云IDE）: 编译、测试、打包
- CD: 交付、部署（以新换旧原则）

### 设计
![CICD设计][1]


### 优点
- 利用组名来进行环境的区分，理论增加无限环境
- 利用 Gitlab 的 Pull Requests 进行权限的审核
- 加入配置中心，渲染初始配置（域名、部署配置 ...）

### 实施步骤
- 部署 gitlab/harbor/cmdb 多个平台给开发、测试和运维进行使用
- 按类型项目对 yaml 文件进行定义，方便以后有同类型项目使用
- 按项目名进行配置中心的定义，方便 yaml 文件根据环境和项目名进行拉取

参考：
[DevOps最小文档实践](https://jingwei.link/2018/06/15/devops-document-practice.html)
[CI/CD是什么？如何理解持续集成、持续交付和持续部署](https://www.redhat.com/zh/topics/devops/what-is-ci-cd)
[DevOps在撰写文档中的实践](https://www.zybuluo.com/xishuixixia/note/179496)


  [1]: http://assets.processon.com/chart_image/5e8ecc9c07912976b718082d.png