function update() {
    let typePictures = document.getElementById('select_type');
    let stylePictures = document.getElementById('select_style');
    let valueType = typePictures.options[typePictures.selectedIndex].value;
    if (valueType > 0) {
        stylePictures.removeAttribute('disabled')
    }
}
