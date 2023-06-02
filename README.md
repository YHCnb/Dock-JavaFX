# Dock-JavaFX

####1 程序的运行环境、安装步骤
	（1）运行环境：JDK 17
	（2）程序的组成部份：程序包容两个jar包，一个AppTools，包含一些程序需要用到的功能函数以及exe可执行文件；一个Dock，为程序主体。
	（3）安装步骤： 
1）安装JRE 17。
2）将程序jar文件复制到计算机上
3）双击jar包即可运行
####2 程序开发平台
	（1）代码行数：约4000行
	（2）开发环境：IntelliJ 2022.1 + JDK 17
####3 程序功能说明：

用户主要针对对象为Windows电脑用户。
主要功能有：
1.支持用户主动动态添加与移除Icon，重新设置Icon的图标，管理Dock栏应用。
2.实现对lnk快捷方式或者exe应用文件的图标、地址等的信息提取。
3.支持用户个性化设置Dock栏外观属性及功能。
4.在Dock栏中实现Icon应用的编辑、运行、退出、所在文件夹、窗口显示隐藏等功能。
5.特殊Icon如仿达（文件资源管理器），时钟，回收站等，实现对特殊常用系统图标及其功能的便捷化管理与运行，如回收站的清理，文件资源管理器的打开……
6.实现Dock应用界面的感应鼠标响应式动态隐藏与显示。
7.实现对用户数据（应用、偏好设置）的记忆化存储。
8.支持用户对Icon图标的位置进行鼠标拖动调整。

（2）选择重点功能，给出屏幕截图，底下给出文字说明。

####1、Icon图标的添加

支持图标的直接拖动添加（包括exe和lnk文件），也可以通过路径获取相应的信息，快捷添加图标。

####2、Icon的启动、打开文件夹、退出等基本操作

Icon的启动、打开文件夹、退出等基本操作通过Icon类中存储的基本信息，将软件基本功能的操作包装成函数。其中一部分是通过直接调用系统命令，另一部分功能因为需要对Windows进行操控，于是通过调用编写的C程序实现。

####3、仿达
	
仿达窗口主要有三个功能，新建仿达窗口（文件资源管理器）、前往文件夹（运行窗口）、打开开始菜单。其中为符合文件夹设定，可以多次点击打开多个仿达窗口。

####4、回收站
回收站具备和Windows回收站相同的功能，可以单击图标或右键打开回收站文件夹，也可右键选择倾倒废纸篓快速清空回收站。

####5、Item元素移动


	支持Icon图标的随意个性化移动管理。同时我们还加入了分隔符元素，支持新建与删除，也支持移动，让窗体更有规划性。

####6、

支持手动隐藏窗体

####7、图标的标签显示

程序中所有Icon或者继承Icon的对象都有其name字段值，通过数据绑定，在鼠标移到相应图标时显示其标签信息。特殊对象如回收站为事先设置，时钟为实时获取当前时间，一般的Icon则是通过读取图标信息初始化设置。

####8、图标的随鼠标变化

使得图标随鼠标所在位置有动态的变化（包括界面整体的大小）

####9、Dock栏的自定义偏好设置




偏好设置中包括5个模块：外观可以自定义设置界面的颜色模式；图标可以设置图标的间距、大小、以及缩放的比例，也可以控制是否缩放等；Dock栏可以控制Dock栏的隐藏时间长短、位置等参数；时间与日期调整时钟的时间格式；关于是一些软件的基本信息，我们项目的Gitee网站，鸣谢等

####4 程序算法说明及面向对象实现技术方案
（1）对用到的算法的说明，简要介绍其算法步骤、时间空间复杂度等
程序中没有用到什么复杂的算法，由于用户图标不多，我们就采取了for循环来处理图标
（2）数据结构和算法的面向对象实现技术方案


程序设计了以下类：

1、TaskControler，WindowsShortcut用于被调用完成特定任务。
2、AddIconControler，SettingControler，Parament，config用于程序界面组件的初始化，其中config组合AddIconControler、SettingControler和Parament。
3、IconImage，IconTag用于图标的视觉显示。
4、App，Dock为应用主应用和主界面，App类继承Application。
5、DockItem，Icon，Separator，Trashcan，Clock，Explorer皆为程序界面组件，其中Icon和Separator继承DockItem类，Trashcan，Clock和Explorer又继承Icon类。

我们的程序包容两个jar包，一个包为AppTools，包含TaskControler等程序需要用到的一些功能类，一个包为Dock，包含程序主体以及一些主要功能函数。

####5 技术亮点、关键点及其解决方案
#####本程序的亮点：
（1）此程序实现对应用程序的便携式管理。
（2）此程序注重用户良好交互体验，体现在：a.鼠标悬停显示应用名称；b.支持应用界面的自动隐藏与显示；c.应用的打开与否有视觉显示；d.鼠标移动，图标有感应式缩放。
（3）此程序支持对一些特殊系统图标进行管理与运行，如Expolorer ，回收站的管理。
（4）此程序支持用户自定义程序界面外观，保存配置。
（5）此程序支持拖动文件至添加图标框来实现图标的添加，更为便捷。
（6）此程序支持lnk快捷方式代替应用程序添加至界面。
#####本程序的技术关键点
（1）我们使用Gitee来管理源码，方便项目对接。
（2）应用了MVC模式来设计整个程序的架构，将业务处理与用户交互界面分离。
 ……
（3）我们使用模块化的方式来管理项目。
（4）我们使用了多种其他语言，如C、C#来编写特殊的应用程序，实现对Windows系统特定功能的实现，以及文件信息的获取。
（5）我们用面向对象实现技术使所有的组件便捷化管理。
（6）我们采用了多线程技术以提升程序的性能。
（7）我们用了大量的数据绑定技术，让管理组件信息更加便捷。
####遇到的技术难点及对应的解决方案：
#####（1）应用程序图标获取问题
问题描述：在JAVA中由于语言限制，无法做到对Windows上文件的图标获取。而使用C语言也难以获取令人满意的高清图标，各种方法得到的最多为48X48，32X32，无法满足需求。
最终的解决方案：通过尝试在C#中通过复杂的步骤获取指定文件的Icon，同时由于此Icon包含了许多不同像素等级的图标信息，通过操作将其分裂为多个独立Icon，再从中找到尺寸最大的一个。由于是在C#中实现的功能，为了方便与Java对接直接将其编译为exe文件，调用时需要传入两个参数，一个realPath用来表示目标文件，一个imgPath用来表示获取的ico的保存地址（保存格式为 文件名.ico），方便调用和管理图片。
#####（2）图标相应鼠标缩放问题
问题描述：为了良好的交互，需要鼠标靠近时图标有缩放效果。而简单地在容器中改变子控件的大小并不会改变容器的大小，只是会让容器的子控件重叠。
最终的解决方案：在HBox中添加用group包装的子控件，实现了容器大小随子控件的大小来变化，而不会造成子控件重叠；设置鼠标move事件，计算子控件与鼠标之间的距离，根据距离的百分比来设置子控件scale，从而达到我们要求的效果。
#####（3）电脑系统功能调用问题
问题描述：由于Java运行在虚拟机上，我们做的Dock栏有许多功能需要调用Windows系统的一些功能，无法在Java上单独实现。
最终的解决方案：通过编写C程序进行对系统的一些操作，比如清空回收站、模拟电脑按键实现一些快捷键功能等。然后再通过与Java对接，包装成相应的函数实现。
#####（4）偏好设置及图标的动态存储问题
问题描述：由于图标是用户在程序运行后动态添加与删除的，也需要让用户能够个性化地调整偏好设置，于是需要将这些参数动态化管理并且能够存储下来。
最终的解决方案：编写renewSettingConfig和renewIconConfig函数，读取相应的参数，分别将改动的参数写入config.ini和icon.ini中，将参数保存，初始化时仅需读取即可。
#####（5）lnk格式文件的真实地址获取问题
问题描述：无法获取lnk文件指定文件的真实地址，就将无法支持lnk添加图标。
最终的解决方案：没有方法可以直接获得lnk的指向地址，但是在查阅后了解由于lnk文件的编码具有一定的规范，可以通过获取lnk的字节编码，再通过规范定位与获取真实地址信息。
#####（6）窗体的毛玻璃效果问题
问题描述：想让JavaFX编写的窗体有像windows7那样的毛玻璃效果。
最终的解决方案：尝试了通过截屏来对截图高斯模糊而达到毛玻璃效果，但这样在程序运行过程中会把自己本窗体截进去，舍弃了该种方法；调用系统api，只能实现对窗体的简单模糊，不能设置圆角等，故舍弃；最终我们舍弃了毛玻璃效果，而通过调节颜色半透明来近似模拟该效果。
#####（7）jar包中exe程序的执行问题
问题描述：Java无法执行jar包中的exe应用程序，但又不想将exe放到程序外部，破换了程序的绿色性。
最终的解决方案：由于我们的exe程序不大，所以在jar包启动时将jar包内部的exe复制出来，这样就实现了我们想要的功能。
####6 简要开发过程
12月4号	对课程内容进行了复习，重点掌握了数据绑定、事件等JavaFX内容
12月11号	搭建了本软件的基本框架，两人的工作通过Gitee进行了整合
12月13号  加入了软件设置界面和分隔符
12月15号  加入了添加图标界面
12月17号	加入了回收站和访达功能图标
12月19号  加入了自动隐藏功能
12月21号  进一步美化了界面，对设置部分的内容进行了完善
12月23号  实现了拖动移动图标位置和拖动添加图标功能
12月24号  修复一些小bug，美化界面，优化动画效果
12月25号  撰写开发文档
####7 个人小结及建议
回想第一个星期由于对Java和JavaFX实操的欠缺，根本没有任何思路来提示我们如何完成我们的这个选题，而且网上根本找不到任何编程语言编写的类似项目（包括GitHub）来供我们来学习参考，于是在第一个星期里我们苦思冥想都没有写出300行代码。到了第二个星期，我们就知道了以我们现在的知识储备和编程能力，是不可能写出效果这么美观的程序，所以我们开始上网学习不断丰富我的JavaFX知识，同时也加了一个QQ群来向群友提问问题；偶然的一次提问中，得知通过嵌套一层group，就可以实现容器的大小会随组件的大小在程序运行中动态地变化，自此一个项目中最重要的问题得到解决。在三、四周编写过程中，我们常常一写就是从上午写到凌晨1点，导致我们其他课程也落下了不少，不过好在最后程序的基本功能已经实现。到了第五周，我们对程序进行了最后的完善和修改，并且实现了获取exe程序图标和通过.lnk文件来获取问价的真实地址，对鼠标移过dock栏时的动画做出了细节上的修改，基本已经达到了我们的预期效果。
陈钰豪：在经过这一个学期的JAVA学习以及结课设计之后，我最大的感悟有两点：
一是基础知识掌握牢靠真的很重要，在结课设计的过程中，其实遇到的状况有很多是譬如模块化管理不熟悉、项目git不正确等造成的。这些问题看似不大，却可以真真实实地阻碍项目开发的进程，同时让你感到特别焦躁，影响巨大。
二是不要被某个技术所局限，学会充分利用不同技术的优势，取长补短。在开发过程中，由于我们的程序需要实现很多对Windows系统本身的操作以及信息获取，而这在Java中有不少时候不能完美的实现。于是在这个项目里我们也写了不少C的程序来实现一些功能，Java仅需调用即可。其中有一个应用Icon图标提取功能由于在C中也得不到令人满意的结果，干脆直接转战C#，最终得到不错的效果。于是有此感悟。
王英泰：本学期的Java课让我第一次走进了图形化界面的大门，也让我第一次和同学合作写出了四千多行代码的程序，程序的最终效果也还算让人满意，基本实现了我们全部的预期目标。通过这一个多月的学习和编写，我感觉我对JavaFX有了不少认知上的提升，对JavaFX的应用能力也得到了很大的锻炼，现在让我用JavaFX写一个小的桌面工具也应该不是问题。我的建议如下：
合理确定自己所选的项目：我们选的项目难度偏大（虽然写完之后感觉难度也一般，但对于一个刚接触图形化界面编程的学生来说无疑是困难的），这让我们在其他许多方面浪费了时间。
遇到不懂的问题要向其他人寻求帮助：限于我自身的知识储备和项目经验，对于一些简单的问题往往会复杂化，所以寻求他人帮助可以帮助减少无用的探索。如在课程
可以上外网来寻求帮助：对于JavaFX来说，国内的资源比较稀缺，虽然有一些网站将国外的问答翻译成中文来供我们查阅，但翻译的质量确实堪忧；而国外的Stack Overflow网站就不错，基本上我搜的问题都能有一个比较满意的解决方案。
