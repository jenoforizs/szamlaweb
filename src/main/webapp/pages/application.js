function szamolBrutto() {
    var netto = $("#nettoErtek").val();
    var afaKulcs = $("#afa").val();
    var brutto = netto * (1+(afaKulcs/100));
    $("#bruttoErtek").attr("value", brutto);
}
