function szamolNetto() {
    var brutto = $("#bruttoErtek").val();
    var netto = brutto * 0.78740157;
    $("#nettoErtek").attr("value", netto);
}
