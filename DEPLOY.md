# Maven Central 发布指南

本文档记录如何将 `sunbay-nexus-sdk-java` 发布到 Maven Central 中央仓库。

---

## ⚡ 快速发布（已配置项目）

**如果你已经完成过首次配置**（GPG 密钥、Maven settings.xml、pom.xml 等），**直接运行以下命令即可**：

```bash
export GPG_TTY=$(tty)
mvn clean deploy -DskipTests
```

**就这么简单！** 输入 GPG 密码后等待发布完成即可。

> 💡 **提示**：首次配置或需要了解详细步骤，请继续阅读下面的内容。

---

## 📋 首次配置（新项目或新环境）

如果你是第一次发布，或者在新环境中配置，请按照以下步骤操作：

### 前置准备

### 1. 注册 Maven Central 账号

1. 访问 https://central.sonatype.com/
2. 注册账号并登录
3. 申请 Namespace（如 `com.sunmi`）

### 2. 生成 GPG 密钥

```bash
# 生成 GPG 密钥
gpg --full-generate-key

# 选择选项：
# 1. 密钥类型：1 (RSA and RSA)
# 2. 密钥长度：4096
# 3. 有效期：0 (永不过期)
# 4. 姓名：Andy Li
# 5. 邮箱：xiaohong.li@sunmi.com
# 6. 注释：Sunbay SDK Developer
# 7. 确认：O
# 8. 设置密码（记住这个密码，签名时需要用到）

# 查看密钥 ID
gpg --list-secret-keys --keyid-format LONG

# 上传公钥到密钥服务器（Maven Central 支持的服务器）
gpg --keyserver hkp://keys.openpgp.org --send-keys YOUR_KEY_ID
```

### 3. 配置 GPG Agent（支持非交互式签名）

```bash
# 创建 gpg-agent.conf
mkdir -p ~/.gnupg
cat > ~/.gnupg/gpg-agent.conf << 'EOF'
pinentry-mode loopback
allow-loopback-pinentry
EOF

# 重启 GPG Agent
gpg-connect-agent reloadagent /bye
```

### 4. 获取 API Token

1. 登录 https://central.sonatype.com/
2. 进入用户设置
3. 生成 API Token
4. 保存 Token（只显示一次）

## 配置步骤

### 1. 配置 Maven settings.xml

创建或编辑 `~/.m2/settings.xml`：

```xml
<settings>
    <servers>
        <server>
            <id>central</id>
            <username>你的用户名或Token用户名</username>
            <password>你的API Token</password>
        </server>
    </servers>
</settings>
```

**注意**：
- `id` 必须与 `pom.xml` 中的 `publishingServerId` 一致（`central`）
- `username` 和 `password` 使用你的 Token 信息

### 2. 检查 pom.xml 配置

确保 `pom.xml` 包含以下配置：

- ✅ `groupId`: `com.sunmi`
- ✅ `artifactId`: `sunbay-nexus-sdk-java`
- ✅ `version`: 版本号（如 `1.0.0`，不能是 SNAPSHOT）
- ✅ `<name>`: 项目名称
- ✅ `<description>`: 项目描述
- ✅ `<url>`: 项目 URL
- ✅ `<licenses>`: 许可证信息
- ✅ `<developers>`: 开发者信息
- ✅ `<scm>`: SCM 信息
- ✅ `maven-source-plugin`: 生成源码 JAR
- ✅ `maven-javadoc-plugin`: 生成 JavaDoc JAR
- ✅ `maven-gpg-plugin`: GPG 签名
- ✅ `central-publishing-maven-plugin`: 发布插件
- ✅ `distributionManagement`: 发布仓库地址

## 发布步骤

### 1. 确保代码已提交

```bash
# 确保所有更改已提交
git status
```

### 2. 运行发布命令

```bash
# 设置 GPG_TTY 环境变量（重要！）
export GPG_TTY=$(tty)

# 执行发布
mvn clean deploy

# 或者跳过测试
mvn clean deploy -DskipTests
```

### 3. 输入 GPG 密码

当提示输入 GPG 密码时，输入你之前设置的 GPG 密钥密码。

### 4. 等待发布完成

发布过程会自动：
1. 清理并编译项目
2. 运行测试（如果未跳过）
3. 生成主 JAR、源码 JAR、JavaDoc JAR
4. 对所有文件进行 GPG 签名
5. 上传到 Maven Central
6. 自动发布（如果配置了 `autoPublish: true`）

## 验证发布

### 1. 检查发布状态

1. 登录 https://central.sonatype.com/
2. 进入 "Publish" -> "Deployments"
3. 查看部署状态：
    - `PUBLISHING`: 正在发布中
    - `PUBLISHED`: 已发布成功

### 2. 验证 Maven Central

发布完成后，等待 10 分钟到 2 小时，然后访问：

```
https://repo1.maven.org/maven2/com/sunmi/sunbay-nexus-sdk-java/1.0.0/
```

### 3. 测试依赖

在其他项目中测试：

```xml
<dependency>
    <groupId>com.sunmi</groupId>
    <artifactId>sunbay-nexus-sdk-java</artifactId>
    <version>1.0.0</version>
</dependency>
```

## 常见问题

### 1. GPG 签名失败

**错误**：`gpg: signing failed: Inappropriate ioctl for device`

**解决**：
```bash
export GPG_TTY=$(tty)
# 然后重新运行 mvn deploy
```

### 2. 401 认证失败

**错误**：`Status: 401`

**解决**：
- 检查 `settings.xml` 中的用户名和密码是否正确
- 确认使用的是 Token 而不是登录密码
- 确认 `server.id` 是 `central`

### 3. 公钥找不到

**错误**：`Could not find a public key by the key fingerprint`

**解决**：
```bash
# 上传公钥到 Maven Central 支持的服务器
gpg --keyserver hkp://keys.openpgp.org --send-keys YOUR_KEY_ID

# 等待几分钟到几小时让公钥同步
```

### 4. 版本号已存在

**错误**：版本号冲突

**解决**：
- Maven Central 不允许覆盖已发布的版本
- 需要更新 `pom.xml` 中的 `<version>` 为新版本号
- 重新发布

## 版本更新流程

1. 更新 `pom.xml` 中的 `<version>`（如 `1.0.0` -> `1.0.1`）
2. 提交代码
3. 运行 `mvn clean deploy`
4. 等待发布完成（或直接使用文档开头的快速发布命令）

## 重要提示

1. **版本号唯一性**：每个版本号只能发布一次，不能覆盖
2. **GPG 密钥**：妥善保管 GPG 密钥和密码，丢失后无法签名新版本
3. **Token 安全**：不要将 Token 提交到代码仓库
4. **发布前测试**：建议先在本地测试，确保代码无误
5. **同步时间**：发布后需要等待同步，通常 10 分钟到 2 小时

## 参考信息

- **项目坐标**：`com.sunmi:sunbay-nexus-sdk-java:1.0.0`
- **GPG 密钥 ID**：`使用 gpg --list-secret-keys --keyid-format LONG 查看`
- **Maven Central 地址**：https://central.sonatype.com/
- **发布 API**：https://central.sonatype.com/api/v1/publishing

---

**最后更新**：2025-12-11

