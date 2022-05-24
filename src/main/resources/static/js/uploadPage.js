const videoBtn = document.getElementById('videoUpload');
const articleBtn = document.getElementById('articlesUpload');
const picturesBtn = document.getElementById('picturesUpload');
const videoForm = document.getElementById('formVideoUpload')
const articlesForm = document.getElementById('formArticlesUpload')
const picturesForm = document.getElementById('formPicturesUpload')
const form = document.getElementById('formUpload')

videoBtn.addEventListener('click', onLoadVideoForm);
articleBtn.addEventListener('click', onLoadArticlesForm);
picturesBtn.addEventListener('click', onLoadPicturesForm);

function onLoadVideoForm() {
    form.style.display = 'block'
    videoForm.style.display = 'block'
    articlesForm.style.display = 'none'
    picturesForm.style.display = 'none'

}
function onLoadArticlesForm() {
    form.style.display = 'block'
    videoForm.style.display = 'none'
    articlesForm.style.display = 'block'
    picturesForm.style.display = 'none'
}
function onLoadPicturesForm() {
    form.style.display = 'block'
    videoForm.style.display = 'none'
    articlesForm.style.display = 'none'
    picturesForm.style.display = 'block'
}

