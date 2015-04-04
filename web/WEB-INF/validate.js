function validate(form1)
{
  for (i = 0; i < form1.length; i++){

       if( (form1.elements[i].value == "")  ){
           alert("You must provide a value for the field named: " + form1.elements[i].name)
           return false
           }

  }
  return true

}


        function showsoft(str)
        {
            xmlhttp=GetXmlHttpObject();
            if (xmlhttp==null)
            {
                alert ("Browser does not support HTTP Request");
                return;
            }
            var url="SubContent";
            url=url+"?q="+str;
            url=url+"&sid="+Math.random();
            xmlhttp.onreadystatechange=stateChanged;
            xmlhttp.open("GET",url,true);
            xmlhttp.send(null);
        }

        function stateChanged()
        {
            if (xmlhttp.readyState==4)
            {
                document.getElementById("1").innerHTML=xmlhttp.responseText;
            }
        }

        function GetXmlHttpObject()
        {
            if (window.XMLHttpRequest)
            {
                // code for IE7+, Firefox, Chrome, Opera, Safari
                return new XMLHttpRequest();
            }
            if (window.ActiveXObject)
            {
                // code for IE6, IE5
                return new ActiveXObject("Microsoft.XMLHTTP");
            }
            return null;
        }

         var xmlhttp1;

        function showsoft1(str1)
        {
            xmlhttp1=GetXmlHttpObject1();
            if (xmlhttp1==null)
            {
                alert ("Browser does not support HTTP Request");
                return;
            }
            var url="Subcontent";
            url=url+"?q="+str1;
            url=url+"&sid="+Math.random();
            xmlhttp1.onreadystatechange=stateChanged1;
            xmlhttp1.open("GET",url,true);
            xmlhttp1.send(null);
        }

        function stateChanged1()
        {
            if (xmlhttp1.readyState==4)
            {
                document.getElementById("2").innerHTML=xmlhttp1.responseText;
            }
        }

        function GetXmlHttpObject1()
        {
            if (window.XMLHttpRequest)
            {
                // code for IE7+, Firefox, Chrome, Opera, Safari
                return new XMLHttpRequest();
            }
            if (window.ActiveXObject)
            {
                // code for IE6, IE5
                return new ActiveXObject("Microsoft.XMLHTTP");
            }
            return null;
        }
