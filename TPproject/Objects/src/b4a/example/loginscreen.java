package b4a.example;


import anywheresoftware.b4a.BA;
import anywheresoftware.b4a.B4AClass;
import anywheresoftware.b4a.BALayout;
import anywheresoftware.b4a.debug.*;

public class loginscreen extends B4AClass.ImplB4AClass implements BA.SubDelegator{
    private static java.util.HashMap<String, java.lang.reflect.Method> htSubs;
    private void innerInitialize(BA _ba) throws Exception {
        if (ba == null) {
            ba = new anywheresoftware.b4a.ShellBA(_ba, this, htSubs, "b4a.example.loginscreen");
            if (htSubs == null) {
                ba.loadHtSubs(this.getClass());
                htSubs = ba.htSubs;
            }
            
        }
        if (BA.isShellModeRuntimeCheck(ba)) 
			   this.getClass().getMethod("_class_globals", b4a.example.loginscreen.class).invoke(this, new Object[] {null});
        else
            ba.raiseEvent2(null, true, "class_globals", false);
    }

 
    public void  innerInitializeHelper(anywheresoftware.b4a.BA _ba) throws Exception{
        innerInitialize(_ba);
    }
    public Object callSub(String sub, Object sender, Object[] args) throws Exception {
        return BA.SubDelegator.SubNotFound;
    }
public anywheresoftware.b4a.keywords.Common __c = null;
public anywheresoftware.b4a.objects.PanelWrapper _wholescreen = null;
public anywheresoftware.b4a.objects.PanelWrapper _infoholder = null;
public anywheresoftware.b4a.objects.ImageViewWrapper _screenimg = null;
public anywheresoftware.b4a.objects.EditTextWrapper _usernamefield = null;
public anywheresoftware.b4a.objects.EditTextWrapper _passwordfield = null;
public anywheresoftware.b4a.objects.ButtonWrapper _loginbtn = null;
public anywheresoftware.b4a.objects.ButtonWrapper _singup = null;
public b4a.example.main _main = null;
public b4a.example.starter _starter = null;
public b4a.example.types _types = null;
public b4a.example.helperfunctions1 _helperfunctions1 = null;
public String  _initialize(b4a.example.loginscreen __ref,anywheresoftware.b4a.BA _ba) throws Exception{
__ref = this;
innerInitialize(_ba);
RDebugUtils.currentModule="loginscreen";
if (Debug.shouldDelegate(ba, "initialize"))
	return (String) Debug.delegate(ba, "initialize", new Object[] {_ba});
RDebugUtils.currentLine=1507328;
 //BA.debugLineNum = 1507328;BA.debugLine="Public Sub Initialize";
RDebugUtils.currentLine=1507329;
 //BA.debugLineNum = 1507329;BA.debugLine="wholescreen.Initialize(\"wholescr\")";
__ref._wholescreen.Initialize(ba,"wholescr");
RDebugUtils.currentLine=1507330;
 //BA.debugLineNum = 1507330;BA.debugLine="usernamefield.Initialize(\"username\")";
__ref._usernamefield.Initialize(ba,"username");
RDebugUtils.currentLine=1507331;
 //BA.debugLineNum = 1507331;BA.debugLine="passwordfield.Initialize(\"password\")";
__ref._passwordfield.Initialize(ba,"password");
RDebugUtils.currentLine=1507332;
 //BA.debugLineNum = 1507332;BA.debugLine="loginbtn.Initialize(\"login\")";
__ref._loginbtn.Initialize(ba,"login");
RDebugUtils.currentLine=1507333;
 //BA.debugLineNum = 1507333;BA.debugLine="screenimg.Initialize(\"\")";
__ref._screenimg.Initialize(ba,"");
RDebugUtils.currentLine=1507334;
 //BA.debugLineNum = 1507334;BA.debugLine="infoholder.Initialize(\"\")";
__ref._infoholder.Initialize(ba,"");
RDebugUtils.currentLine=1507335;
 //BA.debugLineNum = 1507335;BA.debugLine="singup.Initialize(\"register\")";
__ref._singup.Initialize(ba,"register");
RDebugUtils.currentLine=1507337;
 //BA.debugLineNum = 1507337;BA.debugLine="BuildUI";
__ref._buildui(null);
RDebugUtils.currentLine=1507338;
 //BA.debugLineNum = 1507338;BA.debugLine="End Sub";
return "";
}
public anywheresoftware.b4a.objects.PanelWrapper  _asview(b4a.example.loginscreen __ref) throws Exception{
__ref = this;
RDebugUtils.currentModule="loginscreen";
if (Debug.shouldDelegate(ba, "asview"))
	return (anywheresoftware.b4a.objects.PanelWrapper) Debug.delegate(ba, "asview", null);
RDebugUtils.currentLine=1572864;
 //BA.debugLineNum = 1572864;BA.debugLine="Sub AsView As Panel";
RDebugUtils.currentLine=1572865;
 //BA.debugLineNum = 1572865;BA.debugLine="Return wholescreen";
if (true) return __ref._wholescreen;
RDebugUtils.currentLine=1572866;
 //BA.debugLineNum = 1572866;BA.debugLine="End Sub";
return null;
}
public String  _buildui(b4a.example.loginscreen __ref) throws Exception{
__ref = this;
RDebugUtils.currentModule="loginscreen";
if (Debug.shouldDelegate(ba, "buildui"))
	return (String) Debug.delegate(ba, "buildui", null);
RDebugUtils.currentLine=1638400;
 //BA.debugLineNum = 1638400;BA.debugLine="Sub BuildUI";
RDebugUtils.currentLine=1638402;
 //BA.debugLineNum = 1638402;BA.debugLine="screenimg.SetBackgroundImage(LoadBitmap(File.DirA";
__ref._screenimg.SetBackgroundImageNew((android.graphics.Bitmap)(__c.LoadBitmap(__c.File.getDirAssets(),"hotelappscreen.jpg").getObject()));
RDebugUtils.currentLine=1638403;
 //BA.debugLineNum = 1638403;BA.debugLine="screenimg.Gravity = Gravity.FILL";
__ref._screenimg.setGravity(__c.Gravity.FILL);
RDebugUtils.currentLine=1638404;
 //BA.debugLineNum = 1638404;BA.debugLine="wholescreen.AddView(screenimg,0,10%y,100%x,80%y)";
__ref._wholescreen.AddView((android.view.View)(__ref._screenimg.getObject()),(int) (0),__c.PerYToCurrent((float) (10),ba),__c.PerXToCurrent((float) (100),ba),__c.PerYToCurrent((float) (80),ba));
RDebugUtils.currentLine=1638405;
 //BA.debugLineNum = 1638405;BA.debugLine="wholescreen.Color = Colors.ARGB(150,0,0,0)";
__ref._wholescreen.setColor(__c.Colors.ARGB((int) (150),(int) (0),(int) (0),(int) (0)));
RDebugUtils.currentLine=1638408;
 //BA.debugLineNum = 1638408;BA.debugLine="infoholder.Color =  Colors.ARGB(150,0,0,0)";
__ref._infoholder.setColor(__c.Colors.ARGB((int) (150),(int) (0),(int) (0),(int) (0)));
RDebugUtils.currentLine=1638410;
 //BA.debugLineNum = 1638410;BA.debugLine="wholescreen.AddView(infoholder,30%x,100%y,40%x,30";
__ref._wholescreen.AddView((android.view.View)(__ref._infoholder.getObject()),__c.PerXToCurrent((float) (30),ba),__c.PerYToCurrent((float) (100),ba),__c.PerXToCurrent((float) (40),ba),__c.PerYToCurrent((float) (30),ba));
RDebugUtils.currentLine=1638411;
 //BA.debugLineNum = 1638411;BA.debugLine="usernamefield.Gravity = Gravity.LEFT";
__ref._usernamefield.setGravity(__c.Gravity.LEFT);
RDebugUtils.currentLine=1638412;
 //BA.debugLineNum = 1638412;BA.debugLine="usernamefield.Color = Colors.White";
__ref._usernamefield.setColor(__c.Colors.White);
RDebugUtils.currentLine=1638414;
 //BA.debugLineNum = 1638414;BA.debugLine="usernamefield.Text = Main.currentuser.username";
__ref._usernamefield.setText(BA.ObjectToCharSequence(_main._currentuser.username));
RDebugUtils.currentLine=1638415;
 //BA.debugLineNum = 1638415;BA.debugLine="usernamefield.HintColor = Colors.DarkGray";
__ref._usernamefield.setHintColor(__c.Colors.DarkGray);
RDebugUtils.currentLine=1638416;
 //BA.debugLineNum = 1638416;BA.debugLine="usernamefield.SingleLine = True";
__ref._usernamefield.setSingleLine(__c.True);
RDebugUtils.currentLine=1638417;
 //BA.debugLineNum = 1638417;BA.debugLine="usernamefield.TextColor = Colors.Black";
__ref._usernamefield.setTextColor(__c.Colors.Black);
RDebugUtils.currentLine=1638418;
 //BA.debugLineNum = 1638418;BA.debugLine="infoholder.AddView(usernamefield,2.5%x,2.5%y,35%x";
__ref._infoholder.AddView((android.view.View)(__ref._usernamefield.getObject()),__c.PerXToCurrent((float) (2.5),ba),__c.PerYToCurrent((float) (2.5),ba),__c.PerXToCurrent((float) (35),ba),__c.PerYToCurrent((float) (5),ba));
RDebugUtils.currentLine=1638420;
 //BA.debugLineNum = 1638420;BA.debugLine="passwordfield.Gravity = Gravity.LEFT";
__ref._passwordfield.setGravity(__c.Gravity.LEFT);
RDebugUtils.currentLine=1638421;
 //BA.debugLineNum = 1638421;BA.debugLine="passwordfield.Color = Colors.White";
__ref._passwordfield.setColor(__c.Colors.White);
RDebugUtils.currentLine=1638423;
 //BA.debugLineNum = 1638423;BA.debugLine="passwordfield.Text = Main.currentuser.password";
__ref._passwordfield.setText(BA.ObjectToCharSequence(_main._currentuser.password));
RDebugUtils.currentLine=1638424;
 //BA.debugLineNum = 1638424;BA.debugLine="passwordfield.HintColor = Colors.DarkGray";
__ref._passwordfield.setHintColor(__c.Colors.DarkGray);
RDebugUtils.currentLine=1638425;
 //BA.debugLineNum = 1638425;BA.debugLine="passwordfield.PasswordMode = True";
__ref._passwordfield.setPasswordMode(__c.True);
RDebugUtils.currentLine=1638426;
 //BA.debugLineNum = 1638426;BA.debugLine="passwordfield.SingleLine = True";
__ref._passwordfield.setSingleLine(__c.True);
RDebugUtils.currentLine=1638427;
 //BA.debugLineNum = 1638427;BA.debugLine="passwordfield.TextColor = Colors.Black";
__ref._passwordfield.setTextColor(__c.Colors.Black);
RDebugUtils.currentLine=1638428;
 //BA.debugLineNum = 1638428;BA.debugLine="infoholder.AddView(passwordfield,2.5%x,(usernamef";
__ref._infoholder.AddView((android.view.View)(__ref._passwordfield.getObject()),__c.PerXToCurrent((float) (2.5),ba),(int) ((__ref._usernamefield.getTop()+__ref._usernamefield.getHeight())+__c.DipToCurrent((int) (10))),__c.PerXToCurrent((float) (35),ba),__c.PerYToCurrent((float) (5),ba));
RDebugUtils.currentLine=1638430;
 //BA.debugLineNum = 1638430;BA.debugLine="loginbtn.Gravity = Gravity.CENTER";
__ref._loginbtn.setGravity(__c.Gravity.CENTER);
RDebugUtils.currentLine=1638431;
 //BA.debugLineNum = 1638431;BA.debugLine="loginbtn.Color = Colors.White";
__ref._loginbtn.setColor(__c.Colors.White);
RDebugUtils.currentLine=1638432;
 //BA.debugLineNum = 1638432;BA.debugLine="loginbtn.Text = \"Login\"";
__ref._loginbtn.setText(BA.ObjectToCharSequence("Login"));
RDebugUtils.currentLine=1638433;
 //BA.debugLineNum = 1638433;BA.debugLine="loginbtn.TextSize = 20";
__ref._loginbtn.setTextSize((float) (20));
RDebugUtils.currentLine=1638434;
 //BA.debugLineNum = 1638434;BA.debugLine="loginbtn.Textcolor = Colors.Black";
__ref._loginbtn.setTextColor(__c.Colors.Black);
RDebugUtils.currentLine=1638435;
 //BA.debugLineNum = 1638435;BA.debugLine="HelperFunctions1.Apply_ViewStyle(loginbtn,Colors.";
_helperfunctions1._apply_viewstyle(ba,(anywheresoftware.b4a.objects.ConcreteViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.ConcreteViewWrapper(), (android.view.View)(__ref._loginbtn.getObject())),__c.Colors.Black,__c.Colors.RGB((int) (0),(int) (128),(int) (255)),__c.Colors.White,__c.Colors.RGB((int) (77),(int) (166),(int) (255)),__c.Colors.Gray,__c.Colors.Gray,__c.Colors.Gray,(int) (10));
RDebugUtils.currentLine=1638436;
 //BA.debugLineNum = 1638436;BA.debugLine="infoholder.AddView(loginbtn,2.5%x,(passwordfield.";
__ref._infoholder.AddView((android.view.View)(__ref._loginbtn.getObject()),__c.PerXToCurrent((float) (2.5),ba),(int) ((__ref._passwordfield.getTop()+__ref._passwordfield.getHeight())+__c.PerYToCurrent((float) (5),ba)),__c.PerXToCurrent((float) (35),ba),__c.PerYToCurrent((float) (5),ba));
RDebugUtils.currentLine=1638438;
 //BA.debugLineNum = 1638438;BA.debugLine="singup.Gravity = Gravity.CENTER";
__ref._singup.setGravity(__c.Gravity.CENTER);
RDebugUtils.currentLine=1638439;
 //BA.debugLineNum = 1638439;BA.debugLine="singup.Color = Colors.White";
__ref._singup.setColor(__c.Colors.White);
RDebugUtils.currentLine=1638440;
 //BA.debugLineNum = 1638440;BA.debugLine="singup.Text = \"Sing up\"";
__ref._singup.setText(BA.ObjectToCharSequence("Sing up"));
RDebugUtils.currentLine=1638441;
 //BA.debugLineNum = 1638441;BA.debugLine="singup.TextSize = 20";
__ref._singup.setTextSize((float) (20));
RDebugUtils.currentLine=1638442;
 //BA.debugLineNum = 1638442;BA.debugLine="singup.Textcolor = Colors.Black";
__ref._singup.setTextColor(__c.Colors.Black);
RDebugUtils.currentLine=1638443;
 //BA.debugLineNum = 1638443;BA.debugLine="HelperFunctions1.Apply_ViewStyle(singup,Colors.Bl";
_helperfunctions1._apply_viewstyle(ba,(anywheresoftware.b4a.objects.ConcreteViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.ConcreteViewWrapper(), (android.view.View)(__ref._singup.getObject())),__c.Colors.Black,__c.Colors.RGB((int) (0),(int) (128),(int) (255)),__c.Colors.White,__c.Colors.RGB((int) (77),(int) (166),(int) (255)),__c.Colors.Gray,__c.Colors.Gray,__c.Colors.Gray,(int) (10));
RDebugUtils.currentLine=1638444;
 //BA.debugLineNum = 1638444;BA.debugLine="infoholder.AddView(singup,2.5%x,(loginbtn.Top + l";
__ref._infoholder.AddView((android.view.View)(__ref._singup.getObject()),__c.PerXToCurrent((float) (2.5),ba),(int) ((__ref._loginbtn.getTop()+__ref._loginbtn.getHeight())+__c.DipToCurrent((int) (5))),__c.PerXToCurrent((float) (35),ba),__c.PerYToCurrent((float) (5),ba));
RDebugUtils.currentLine=1638446;
 //BA.debugLineNum = 1638446;BA.debugLine="infoholder.SetLayoutAnimated(1000,30%x,30%y,40%x,";
__ref._infoholder.SetLayoutAnimated((int) (1000),__c.PerXToCurrent((float) (30),ba),__c.PerYToCurrent((float) (30),ba),__c.PerXToCurrent((float) (40),ba),__c.PerYToCurrent((float) (30),ba));
RDebugUtils.currentLine=1638447;
 //BA.debugLineNum = 1638447;BA.debugLine="End Sub";
return "";
}
public String  _class_globals(b4a.example.loginscreen __ref) throws Exception{
__ref = this;
RDebugUtils.currentModule="loginscreen";
RDebugUtils.currentLine=1441792;
 //BA.debugLineNum = 1441792;BA.debugLine="Sub Class_Globals";
RDebugUtils.currentLine=1441793;
 //BA.debugLineNum = 1441793;BA.debugLine="Dim wholescreen As Panel";
_wholescreen = new anywheresoftware.b4a.objects.PanelWrapper();
RDebugUtils.currentLine=1441794;
 //BA.debugLineNum = 1441794;BA.debugLine="Dim infoholder As Panel";
_infoholder = new anywheresoftware.b4a.objects.PanelWrapper();
RDebugUtils.currentLine=1441795;
 //BA.debugLineNum = 1441795;BA.debugLine="Dim screenimg As ImageView";
_screenimg = new anywheresoftware.b4a.objects.ImageViewWrapper();
RDebugUtils.currentLine=1441796;
 //BA.debugLineNum = 1441796;BA.debugLine="Dim usernamefield As EditText";
_usernamefield = new anywheresoftware.b4a.objects.EditTextWrapper();
RDebugUtils.currentLine=1441797;
 //BA.debugLineNum = 1441797;BA.debugLine="Dim passwordfield As EditText";
_passwordfield = new anywheresoftware.b4a.objects.EditTextWrapper();
RDebugUtils.currentLine=1441798;
 //BA.debugLineNum = 1441798;BA.debugLine="Dim loginbtn As Button";
_loginbtn = new anywheresoftware.b4a.objects.ButtonWrapper();
RDebugUtils.currentLine=1441799;
 //BA.debugLineNum = 1441799;BA.debugLine="Dim singup As Button";
_singup = new anywheresoftware.b4a.objects.ButtonWrapper();
RDebugUtils.currentLine=1441801;
 //BA.debugLineNum = 1441801;BA.debugLine="End Sub";
return "";
}
public String  _login_click(b4a.example.loginscreen __ref) throws Exception{
__ref = this;
RDebugUtils.currentModule="loginscreen";
if (Debug.shouldDelegate(ba, "login_click"))
	return (String) Debug.delegate(ba, "login_click", null);
RDebugUtils.currentLine=1703936;
 //BA.debugLineNum = 1703936;BA.debugLine="Sub login_Click";
RDebugUtils.currentLine=1703940;
 //BA.debugLineNum = 1703940;BA.debugLine="Log(Main.currentuser)";
__c.Log(BA.ObjectToString(_main._currentuser));
RDebugUtils.currentLine=1703941;
 //BA.debugLineNum = 1703941;BA.debugLine="CallSub(Main,\"ShowUI\")";
__c.CallSubDebug(ba,(Object)(_main.getObject()),"ShowUI");
RDebugUtils.currentLine=1703942;
 //BA.debugLineNum = 1703942;BA.debugLine="End Sub";
return "";
}
}