<?
 session_name();
 session_start();
 $FICHERO2=$_SESSION['F2'];

if(file_exists($FICHERO2))
{
unlink($FICHERO2);
}
?>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<title>FyQ DE PAR EN PAR. DISE&Ntilde;O DE BATER&Iacute;A DE PREGUNTAS</title>
<style type="text/css">
<!--
body {
	background-color: #CCFFFF;
}
-->
</style></head>

<body>


<form action="datoscuestfyqparpar.php" method="post" name="formulario2">
  <div align="center">
    <h1><strong>  <img src="atomo.png" width="30" height="25">&nbsp;&nbsp;&nbsp;FyQ DE PAR EN PAR. DISE&Ntilde;O DE BATER&Iacute;A DE PREGUNTAS&nbsp;&nbsp; <img src="atomo.png" width="30" height="25"></strong></h1>
    <table width="100%"  border="1">
      <tr>
        <td colspan="10"><div align="center">
            <h2>DATOS GENERALES </h2>
        </div></td>
      </tr>
      <tr>
        <td colspan="10"><div align="center"><strong>Nombre y extensi&oacute;n del fichero (la extensi&oacute;n debe ser .txt y en min&uacute;sculas):
                  <input name="nomfic" type="text" id="nomfic" value="nombre.txt" size="62" maxlength="60">
        </strong></div></td>
      </tr>
      <tr>
        <td colspan="10"><div align="center"><strong>Nombre y apellido del autor de la bater&iacute;a de preguntas:
                  <input name="nombautor" type="text" id="nombautor" value="Nombre y apellido del autor" size="52" maxlength="50">
        </strong></div></td>
      </tr>
      <tr>
        <td colspan="10"><div align="center"><strong>TIEMPO :
                  <select name="T" size="1" id="T">
                    <option>30</option>
                    <option>31</option>
                    <option>32</option>
                    <option>33</option>
                    <option>34</option>
                    <option>35</option>
                    <option>36</option>
                    <option>37</option>
                    <option>38</option>
                    <option>39</option>
                    <option>40</option>
                    <option>41</option>
                    <option>42</option>
                    <option>43</option>
                    <option>44</option>
                    <option>45</option>
                    <option>46</option>
                    <option>47</option>
                    <option>48</option>
                    <option>49</option>
                    <option>50</option>
                    <option>51</option>
                    <option>52</option>
                    <option>53</option>
                    <option>54</option>
                    <option>55</option>
                    <option>56</option>
                    <option>57</option>
                    <option>58</option>
                    <option>59</option>
                    <option value="60" selected>60</option>
                    <option>61</option>
                    <option>62</option>
                    <option>63</option>
                    <option>64</option>
                    <option>65</option>
                    <option>66</option>
                    <option>67</option>
                    <option>68</option>
                    <option>69</option>
                    <option>70</option>
                    <option>71</option>
                    <option>72</option>
                    <option>73</option>
                    <option>74</option>
                    <option>75</option>
                    <option>76</option>
                    <option>77</option>
                    <option>78</option>
                    <option>79</option>
                    <option>80</option>
                    <option>81</option>
                    <option>82</option>
                    <option>83</option>
                    <option>84</option>
                    <option>85</option>
                    <option>86</option>
                    <option>87</option>
                    <option>88</option>
                    <option>89</option>
                    <option>90</option>
                    <option>91</option>
                    <option>92</option>
                    <option>93</option>
                    <option>94</option>
                    <option>95</option>
                    <option>96</option>
                    <option>97</option>
                    <option>98</option>
                    <option>99</option>
            </select>
          </strong></div>
            <div align="center"></div>          <div align="center"><strong>          </strong></div>            <div align="center"></div>            <div align="center"></div>            <div align="center"></div></td>
      </tr>
      <tr>
        <td colspan="10">&nbsp;</td>
      </tr>
      <tr>
        <td colspan="10"><div align="center">
            <h2>PAREJAS
              <input name="num1" type="hidden" id="num1" value="PISTAS">
            </h2>
        </div></td>
      </tr>
      <tr>
        <td width="24%"><div align="center"></div>          <strong>
          </strong></td>
        <td width="8%" colspan="-1"><div align="center"><strong>1&ordf;</strong></div></td>
        <td width="16%"><div align="center"><strong>
            <input name="p1" type="text" id="nombbateria" size="25" maxlength="19">
        </strong></div></td>
        <td width="16%"><div align="center"><strong>
            <input name="pE1" type="text" id="nombbateria" size="20" maxlength="13">
        </strong></div>          <div align="center"></div></td>
        <td width="32%" colspan="2">&nbsp;</td>
      </tr>
      <tr>
        <td><div align="center"></div>
            <strong> </strong></td>
        <td colspan="-1"><div align="center"><strong>2&ordf;</strong></div></td>
        <td><div align="center"><strong>
            <input name="p2" type="text" id="nombbateria" size="25" maxlength="19">
        </strong></div></td>
        <td><div align="center"><strong>
            <input name="pE2" type="text" id="nombbateria" size="20" maxlength="13">
          </strong></div>
            <div align="center"></div></td>
        <td colspan="2">&nbsp;</td>
      </tr>
      <tr>
        <td><div align="center"></div>
            <strong> </strong></td>
        <td colspan="-1"><div align="center"><strong>3&ordf;</strong></div></td>
        <td><div align="center"><strong>
            <input name="p3" type="text" id="nombbateria" size="25" maxlength="19">
        </strong></div></td>
        <td><div align="center"><strong>
            <input name="pE3" type="text" id="nombbateria" size="20" maxlength="13">
          </strong></div>
            <div align="center"></div></td>
        <td colspan="2">&nbsp;</td>
      </tr>
      <tr>
        <td><div align="center"></div>
            <strong> </strong></td>
        <td colspan="-1"><div align="center"><strong>4&ordf;</strong></div></td>
        <td><div align="center"><strong>
            <input name="p4" type="text" id="nombbateria" size="25" maxlength="19">
        </strong></div></td>
        <td><div align="center"><strong>
            <input name="pE4" type="text" id="nombbateria" size="20" maxlength="13">
          </strong></div>
            <div align="center"></div></td>
        <td colspan="2">&nbsp;</td>
      </tr>
      <tr>
        <td><div align="center"></div>
            <strong> </strong></td>
        <td colspan="-1"><div align="center"><strong>5&ordf;</strong></div></td>
        <td><div align="center"><strong>
            <input name="p5" type="text" id="nombbateria" size="25" maxlength="19">
        </strong></div></td>
        <td><div align="center"><strong>
            <input name="pE5" type="text" id="nombbateria" size="20" maxlength="13">
          </strong></div>
            <div align="center"></div></td>
        <td colspan="2">&nbsp;</td>
      </tr>
      <tr>
        <td><div align="center"></div>
            <strong> </strong></td>
        <td colspan="-1"><div align="center"><strong>6&ordf;</strong></div></td>
        <td><div align="center"><strong>
            <input name="p6" type="text" id="nombbateria" size="25" maxlength="19">
        </strong></div></td>
        <td><div align="center"><strong>
            <input name="pE6" type="text" id="nombbateria" size="20" maxlength="13">
          </strong></div>
            <div align="center"></div></td>
        <td colspan="2">&nbsp;</td>
      </tr>
      <tr>
        <td><div align="center"></div>
            <strong> </strong></td>
        <td colspan="-1"><div align="center"><strong>7&ordf;</strong></div></td>
        <td><div align="center"><strong>
            <input name="p7" type="text" id="nombbateria" size="25" maxlength="19">
        </strong></div></td>
        <td><div align="center"><strong>
            <input name="pE7" type="text" id="nombbateria" size="20" maxlength="13">
          </strong></div>
            <div align="center"></div></td>
        <td colspan="2">&nbsp;</td>
      </tr>
      <tr>
        <td><div align="center"></div>
            <strong> </strong></td>
        <td colspan="-1"><div align="center"><strong>8&ordf;</strong></div></td>
        <td><div align="center"><strong>
            <input name="p8" type="text" id="nombbateria" size="25" maxlength="19">
        </strong></div></td>
        <td><div align="center"><strong>
            <input name="pE8" type="text" id="nombbateria" size="20" maxlength="13">
          </strong></div>
            <div align="center"></div></td>
        <td colspan="2">&nbsp;</td>
      </tr>
      <tr>
        <td><div align="center"></div>
            <strong> </strong></td>
        <td colspan="-1"><div align="center"><strong>9&ordf;</strong></div></td>
        <td><div align="center"><strong>
            <input name="p9" type="text" id="nombbateria" size="25" maxlength="19">
        </strong></div></td>
        <td><div align="center"><strong>
            <input name="pE9" type="text" id="nombbateria" size="20" maxlength="13">
          </strong></div>
            <div align="center"></div></td>
        <td colspan="2">&nbsp;</td>
      </tr>
      <tr>
        <td colspan="10">&nbsp;</td>
      </tr>
      <tr>
        <td colspan="2"><div align="center">          </div>
            <div align="center"></div>
            <div align="center"></div>
            <div align="center"></div>
            <div align="center"></div>
            <div align="center"></div></td>
        <td><div align="center">
          <input name="Enviar" type="submit" id="Enviar" value="Enviar">
        </div></td>
        <td><div align="center">
          <input name="borrar" type="reset" id="borrar" value="Borrar">
        </div></td>
        <td colspan="4"><div align="center">          </div>
            <div align="center"></div>
            <div align="center"></div>
            <div align="center"></div>
            <div align="center"></div>
            <div align="center"></div></td>
      </tr>
    </table>
    <p><strong>Nota: Repasar bien los datos antes de enviar el formulario, ya que se si vuelve a la p&aacute;gina los datos introducidos se perder&aacute;n </strong></p>
    <p><strong>Para que este formulario genere el fichero de texto, debe estar siendo utilizado bajo un servidor que utilice php. </strong></p>
    <p><br>
    </p>
  </div>
</form>
</body>
</html>
