<%--
  Created by IntelliJ IDEA.
  User: sam.pan
  Date: 2017/3/9
  Time: 14:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<footer class="footer">
    <div class="tac">
        <div class="inb">
            <h1 class="num">尊享热线：400-882-6669</h1>
            <ul>
                <li><a href="contact.html">联系我们</a></li>
                <li><a href="feedback.html">投诉建议</a></li>
            </ul>
        </div>
    </div>
    <div class="clear">
        <div class="maxsize">
            <p class="fl">地址：新会佰瑞林陈皮</p>
            <p class="fr">Copyright © 2017 brl.com 版权所有. 粤ICP备05019014号-1</p>
        </div>
    </div>
</footer>

<ins id="newBridge"><!-- target: nodeBoard -->
    <ins class="nb-nodeboard-base nb-nodeboard-position-base nb-nodeboard-left-bottom nb-show" id="nb_nodeboard"
         style="left: 1px; right: auto; top: auto; bottom: 1px; margin-left: 0px; margin-top: 0px; height: auto;">
        <ins class="nb-nodeboard-contain-base nb-nodeboard-contain">
            <ins class="nb-nodeboard-top nb-nodeboard-top-7">
                <ins class="nb-head-title">请您留言</ins>
                <ins class="nb-nodeboard-close " id="nb_nodeboard_close" data-type="min"></ins>
            </ins>
            <form id="nb_nodeboard_form" autocomplete="off" class="nb-board-form"
                  action="${ctx}/" method="post"
                  accept-charset="utf-8">
                <ins id="nb_nodeboard_set" class="nb-nodeboard-set" style="display: block;">
                    <ins class="nb-nodeboard-content">
                        <textarea id="nb-nodeboard-set-content-js" name="content"
                                                                data-ph="请在此输入留言内容，我们会尽快与您联系。（必填）"
                                                                placeholder="请在此输入留言内容，我们会尽快与您联系。（必填）"
                                                                class="nb-nodeboard-set-content"></textarea></ins>
                    <ins class="nb-nodeboard-name" style="z-index:4;">
                        <ins class="nb-nodeboard-icon nodeName"></ins>
                        <input id="nb_nodeboard_set_name" data-write="0" name="visitorName" maxlength="30"
                               class="nb-nodeboard-input" type="text" data-ph="姓名" placeholder="姓名"></ins>
                    <ins class="nb-nodeboard-name" id="nb_nodeboard_phone" style="z-index:3;">
                        <ins class="nb-nodeboard-icon nodePhone"></ins>
                        <input id="nb_nodeboard_set_phone" name="visitorPhone" maxlength="30" class="nb-nodeboard-input"
                               data-write="1" type="text" data-ph="电话（必填）" placeholder="电话（必填）"></ins>
                    <ins class="nb-nodeboard-name" id="nb_nodeboard_mail" style="z-index:2;">
                        <ins class="nb-nodeboard-icon nodeMail"></ins>
                        <input id="nb_nodeboard_set_email" name="visitorEmail" maxlength="50" class="nb-nodeboard-input"
                               type="text" data-write="0" data-ph="邮箱" placeholder="邮箱"></ins>
                    <ins class="nb-nodeboard-name" style="z-index:1;">
                        <ins class="nb-nodeboard-icon nodeAddress"></ins>
                        <input id="nb_nodeboard_set_address" name="visitorAddress" class="nb-nodeboard-input"
                               maxlength="50" type="text" data-write="0" data-ph="地址" placeholder="地址"></ins>
                </ins>
            </form>
            <ins class="nb-nodeboard-success" id="nb_nodeboard_success" style="display: none;">
                <ins class="nb-success-box">
                    <ins class="nb-success-icon"></ins>
                    <ins class="nb-success-title" id="nb_node_messagetitle">感谢留言</ins>
                    <ins class="nb-success-content" id="nb_node_messagecontent">我们会尽快与您联系</ins>
                    <ins id="sucess_close" class="nb-sucess-close">关闭</ins>
                </ins>
            </ins>
            <ins class="nb-nodeboard-send" id="nb_node_contain" style="display: block;">
                <ins id="nb_nodeboard_send" class="nb-nodeboard-send-btn nb-nodeboard-send-btn-7">发送</ins>
            </ins>
        </ins>
    </ins><!-- end -->
    <!-- target: iconIcon -->
    <ins class="nb-icon-wrap nb-icon-base icon-right-center nb-icon-skin-2 nb-icon-icon nb-customer-icon-style"
         id="nb_icon_wrap" style="margin-top:-50px;width:245px;">
        <ins class="nb-icon-inner-wrap"
             style="height:100px;width:245px;background-image: url(//sgoutong.baidu.com/static/style/images/0cdbeb5753fd465180e5ef26358ccbfa.png);;">
            <ins class="nb-icon-bridge0 nb-icon-bridge-base" data-type="iconInvite"></ins>
        </ins>
    </ins><!-- end -->
    <!-- target: invite -->
    <ins class="nb-invite-wrap nb-invite-wrap-base nb-position-base nb-middle customer-invite-style "
         id="nb_invite_wrap" style="width:372px;display:none;margin-left: -186px;margin-top: -80px;">
        <ins class="nb-invite-body nb-invite-skin-0"
             style="height:160px;background:url(//sgoutong.baidu.com/static/style/images/73a6a56f57184a2dbc2b07d3a9d2ff05.png)">
            <ins class="nb-invite-tool nb-invite-tool-base" id="nb_invite_tool" style=""></ins>
            <ins class="nb-invite-text nb-invite-text-base">
                <ins class="nb-invite-welcome nb-invite-welcome-base nb-show" id="nb_invite_welcome"><p
                        style="color: #fff"><span
                        style="color: rgb(0, 0, 0); font-size: 12pt;">欢迎来到燕之屋，请问有什么可以帮您？</span></p></ins>
            </ins>
            <ins class="nb-invite-btn-base nb-invte-btns-0">
                <ins class="nb-invite-cancel nb-invite-cancel-base" id="nb_invite_cancel">稍后再说</ins>
                <ins class="nb-invite-ok nb-invite-ok-base" id="nb_invite_ok"
                     style="background-color:#c5a955;color:#4f4d4d;">现在咨询
                </ins>
            </ins>
        </ins>
    </ins><!-- end --></ins>