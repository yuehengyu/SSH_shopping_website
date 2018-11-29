# SSH_shopping_website
## I used the SSH to develop this website bymeself. I will brief introduce what features I implemented.

## 1.1.1 user modules
### Register

1. Front desk JS check. <br>
2. Use AJAX to complete the asynchronous check of the username.<br>
3. Back-end Struts2 verification.<br>
4. Verification code.<br>
5. Send an activation email.<br>
6. Save user information to the database.<br>

### Activation

`1. Click on the link to complete the activation: `<br>
* Query the database for the user based on the activation code.<br>
* If there is: Activate. (Clear the activation code field.
* Modify the user's status.<br>
