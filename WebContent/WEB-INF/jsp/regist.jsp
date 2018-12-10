<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<%@ taglib uri="/struts-tags" prefix="s"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Member register</title>
<link href="${pageContext.request.contextPath}/css/common.css"
	rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/css/register.css"
	rel="stylesheet" type="text/css" />

<script>
	function checkForm() {
		var username = document.getElementById("username").value;
		if (username == null || username == '') {
			alert("Username can not be empty");
			return false;
		}

		var password = document.getElementById("password").value;
		if (password == null || password == '') {
			alert("Password can not be empty");
			return false;
		}

		var repassword = document.getElementById("repassword").value;
		if (repassword != password) {
			alert("Two password entries are inconsistent");
			return false;
		}
	}
	//ajax check username
	function checkUsername() {
		var username = document.getElementById("username").value;
		var xhr = createXmlHttp();
		xhr.onreadystatechange = function() {
			if (xhr.readyState == 4) {
				if (xhr.status == 200) {
					document.getElementById("checkusername").innerHTML = xhr.responseText;
				}
			}
		}
		xhr.open("GET",
				"${pageContext.request.contextPath}/user_findByName.action?time="
						+ new Date().getTime() + "&username=" + username, true);
		xhr.send(username);
	}

	function createXmlHttp() {
		var xmlHTttp;
		try {
			xmlHttp = new XMLHttpRequest();
		} catch (e) {
			try {
				xmlHttp = new ActiveXObject("Msxml2.XMLHTTP");
			} catch (e) {
				try {
					xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
				} catch (e) {
				}
			}
		}
		return xmlHttp;
	}
	
	
	function change(){
		var image=document.getElementById("checkImg");
		image.src="${pageContext.request.contextPath}/checkImg.action?"+new Date().getTime();
	}
</script>
</head>
<body>
	<%@include file="menu.jsp"%>
	<div class="container register">
		<div class="span24">
			<div class="wrap">
				<div class="main clearfix">
					<div class="title">
						<strong>Member</strong>USER REGISTER

					</div>
					<div>
						<s:actionerror />
					</div>
					<form id="registerForm"
						action="${pageContext.request.contextPath}/user_regist.action"
						method="post" novalidate="novalidate"
						onsubmit="return checkForm();">
						<table>
							<tbody>
								<tr>
									<th><span class="requiredField">*</span>Username:</th>
									<td><input type="text" id="username" name="username"
										class="text" maxlength="20" onblur="checkUsername()" /> <span
										id="checkusername"></span></td>
								</tr>
								<tr>
									<th><span class="requiredField">*</span>Password:</th>
									<td><input type="password" id="password" name="password"
										class="text" maxlength="20" autocomplete="off" /> <span><s:fielderror
												fieldName="password" /></span></td>
								</tr>
								<tr>
									<th><span class="requiredField">*</span>Confirm password:</th>
									<td><input id="repassword" type="password"
										name="repassword" class="text" maxlength="20"
										autocomplete="off" /></td>
								</tr>
								<tr>
									<th><span class="requiredField">*</span>E-mail:</th>
									<td><input type="text" id="email" name="email"
										class="text" maxlength="200" /> <span><s:fielderror
												fieldName="email" /></span></td>
								</tr>
								<tr>
									<th>Name:</th>
									<td><input type="text" name="name" class="text"
										maxlength="200" /> <span><s:fielderror
												fieldName="name" /></span></td>
								</tr>
								<tr>
									<th>Phone:</th>
									<td><input type="text" name="phone" class="text" /></td>
								</tr>

								<tr>
									<th>Address:</th>
									<td><input type="text" name="addr" class="text"
										maxlength="200" /> <span><s:fielderror
												fieldName="addr" /></span></td>
								</tr>
								<tr>
									<th><span class="requiredField">*</span>Verification code:</th>
									<td><span class="fieldSet"> <input type="text"
											id="checkcode" name="checkcode" class="text captcha"
											maxlength="4" autocomplete="off" /><img id="checkImg"
											class="captchaImage" onclick="change()"
											src="${pageContext.request.contextPath}/checkImg.action"
											title="Click to change" /></span></td>
								</tr>
								<tr>
									<th>&nbsp;</th>
									<td><input type="submit" class="submit"
										value="Agree and submit" /></td>
								</tr>
								<tr>
									<th>&nbsp;</th>
									<td>Registration Agreement</td>
								</tr>
								<tr>
									<th>&nbsp;</th>
									<td>
										<div id="agreement" class="agreement" style="height: 200px;">
											<p>Dear users, you are welcome to register as a member of
												this website. Please read the following carefully. If the
												user does not agree to any of the terms of the Terms of
												Service, please do not register or use the services of this
												website. By registering the program through this website,
												the user has reached an agreement with this website and
												voluntarily accepts all the contents of these Terms of
												Service. Thereafter, the User shall not make any form of
												defense without reading the contents of these Terms of
												Service.</p>
											<p>
												I.Confirmation and acceptance of the terms of service of
												this site<br>The ownership and operation of the
													services covered by this website are owned by this website.
													The services provided on this website must be performed in
													strict accordance with the terms of service and operating
													rules issued by them. When a user enjoys any of the
													services on this website, the scope of the terms of service
													and all products and services on this website are subject
													to these terms of service.。 
											</p>
											<p>
												II.Service introduction<br>This website uses its own
													operating system to provide users with various services
													through the Internet. The user must: 1. Provide devices
													such as personal computers, mobile phones or other
													connected devices. 2. Personal access to the Internet and
													payment of fees associated with this service. 
											</p>
											<p>
												III、The user may not post the following illegal information
												on this website.<br>1. Oppose the basic principles
													established by the Constitution;2. Endanger national
													security, divulge state secrets, subvert state power, and
													undermine national unity; 3. Damage to national honours and
													interests; 4. Inciting national hatred, ethnic
													discrimination, and undermining national unity; 5. Destroy
													the national religious policy and promote cults and feudal
													superstitions;6. Spread rumors, disrupt social order, and
													undermine social stability; 7. Spreading obscenity,
													pornography, gambling, violence, murder, terror or
													abetment; 8. Insulting or slandering others and infringing
													upon the lawful rights and interests of others; 9. Contains
													other content prohibited by laws and administrative
													regulations. 
											</p>
											<p>
												IV、Related personal information<br>User agrees: 1.
													Provide timely, detailed and accurate personal information.
													2. Agree to receive information from this website. 3.
													Continuously update the registration data to meet timely,
													detailed and accurate requirements. All original typed
													materials will be cited as registration materials. 4. This
													website does not disclose the user's name, address, email
													address and pen name. Except for the following: a) The user
													authorizes the site to disclose this information. b) The
													corresponding laws and procedures require the site to
													provide the user's personal information. 
											</p>
											<p>
												V、Modification of the Terms of Service<br>This website
													reserves the right to modify the terms of service as
													necessary. Once the terms and services have changed, this
													website will prompt you to modify the content on important
													pages. If you do not agree with the changed content, the
													user can voluntarily cancel the information service of this
													website. If the user continues to enjoy the information
													service of this website, it is deemed to accept the change
													of the terms of service. 
											</p>
											<p>
												VI、User privacy system<br>Respecting the privacy of
													users is a basic policy of this website. Therefore, this
													website will not disclose, edit or disclose its
													registration materials and non-public content stored on
													this website without the authorization of legitimate users,
													unless there is legal permission or the website believes
													that such information is disclosed on the basis of good
													faith. It is necessary in the following four situations: 1.
													Comply with the relevant laws and regulations and follow
													the legal service procedures of this website. 2. Maintain
													the trademark ownership of this website.3. In an emergency,
													we strive to maintain the privacy of our users and the
													general public. 4. Meet other relevant requirements. 
											</p>
											<p>
												VII、User account, password, and security<br>Once the
													user has successfully registered, they will get a password
													and username. Users should carefully and reasonably save
													and use the username and password. If you do not keep your
													account and password secure, you will be fully responsible.
													In addition, each user is solely responsible for all
													activities and events in their account. You can change your
													password at any time by following the instructions. If the
													user finds any illegal use of the user account or a
													security breach, please notify this website immediately.
													VIII、 Refusal to provide guarantee The user expressly
													agrees that the use of the information service is at the
													user's own risk. This website does not guarantee that the
													service will not be interrupted. There is no guarantee for
													the timeliness, security and error of the service, but it
													will be within the scope of capability to avoid mistakes. 
											</p>
											<p>
												IX、Limited Liability<br>If the sales system of the
													station crashes or fails to function due to force majeure
													or other reasons beyond the control of the site, the online
													transaction cannot be completed or the relevant information
													and records will be lost. The site will assist in dealing
													with the aftermath as much as possible and strive to make
													the customer Protect from economic losses and try to avoid
													such damage. 
											</p>
											<p>
												X、Storage and restriction of user information<br>This
													site has the right to determine whether the user's behavior
													complies with the provisions of national laws and
													regulations and the terms of service of this site. If the
													user violates the terms of service of this website, this
													website has the right to interrupt the service. 
											</p>
											<p>
												XI、User Management<br>The user is solely responsible
													for the content posted. User's use of the Service is based
													on all national, local and international legal standards
													applicable to the Site. Users must follow: 1. The use of
													web services is not illegal. 2. Do not interfere with or
													confuse network services. 3. Comply with all network
													protocols, regulations, procedures, and practices that use
													network services. Users must promise not to transmit any
													illegal, harassing, slanderous, abusive, fearful, harmful,
													vulgar, obscene and other information. In addition, users
													are not able to transmit information about what constitutes
													a criminal act; they cannot transmit information that
													contributes to domestic adverse conditions and national
													security; they cannot transmit any information that does
													not comply with local, national, and international laws. It
													is prohibited to illegally enter other computer systems
													without permission. If the user's behavior does not meet
													the above mentioned terms of service, the site will make an
													independent judgment to immediately cancel the user service
													account. Users are legally responsible for their actions on
													the Internet. If the user distributes and disseminates
													reactionary, pornographic or other information that
													violates national laws on this site, the system's system
													records may be used as evidence that the user violated the
													law. 
											</p>
											<p>
												XII、Notice<br>All notices sent to users can be sent via
													announcements on important pages or by email or regular
													mail. Modifications to the Terms of Service, changes to the
													Service, or notices of other important events will be made
													in this form. 
											</p>
											<p>
												XIII、Ownership of information content<br>The
													information content defined on this website includes: Text,
													software, sound, photos, videos, graphics; all content in
													the advertisement; other information provided by the
													website for the user. All of this content is protected by
													copyright, trademark, label and other property ownership
													laws. Therefore, users can only use these content under the
													authorization of this website and advertisers, and cannot
													copy, reproduce or create derivative products related to
													the content. All the articles on this site are copyrighted
													by the original author and the site. Anyone who needs to
													reprint the article on this site must obtain the
													authorization of the original author or the site. 
											</p>
											<p>
												IXV、Legal<br>The conclusion, execution and
													interpretation of this Agreement and resolution of disputes
													shall be governed by the laws of the People's Republic of
													China. The User and this website agree to submit to the
													jurisdiction of the court where the website is located. In
													the event of any conflict between the terms of service of
													this website and the laws of the People's Republic of
													China, these terms will be completely re-interpreted in
													accordance with the law, while other terms remain binding
													on the user. 
											</p>
										</div>
									</td>
								</tr>
							</tbody>
						</table>
						<div class="login">
							<div class="ad">
								<dl>
									<dt>Sign up and enjoy</dt>
									<dd>Genuine guarantee, formal invoice</dd>
									<dd>Cash on delivery, membership service</dd>
									<dd>Free return, after-sales door-to-door</dd>
								</dl>
							</div>
							<dl>
								<dt>Already have an account?</dt>
								<dd>
									Sign in now to experience online shopping！ <a href="${pageContext.request.contextPath}/user_loginPage.action">Sign in now</a>
								</dd>
							</dl>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
	<%@include file="footer.jsp"  %>

</body>
</html>