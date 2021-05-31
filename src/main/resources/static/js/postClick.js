function loadPublication(data) {

    document.getElementById("post-modal-image").src = data.image;
    document.getElementById("post-modal-text").innerText = data.text;
    document.getElementById("post-modal-avgRating").innerText = data.media + '⭐';
    document.getElementById("post-modal-ratingAmount").innerText = data.numerototalval + '🧐';

    document.getElementById("page-mask").style.display = "flex";

}

function generateComment(comment) {
    let temp = document.getElementsByTagName("template")[0];
    let clon = temp.content.cloneNode(true);

    if (comment.pfp)
        clon.children[0].children[0].children[0].children[0].src = comment.pfp;
    else
        clon.children[0].children[0].children[0].children[0].src = "https://icon-library.com/images/no-profile-pic-icon/no-profile-pic-icon-11.jpg";

    clon.children[0].children[0].children[1].children[0].innerHTML = `<p class="username">${comment.user}: <span class="comment"> ${comment.text}</span></p>`;
    document.getElementById("post-modal-comments").appendChild(clon);

}

function loadComments(comments) {comments.forEach(generateComment);}

function onPostClicked(e) {

    // Se vacian los comentarios que habia antes en el modal.
    $('#post-modal-comments').empty()

    // Se llama a /posts/postID para obtener la info de la publicacion
    $.get("/posts/" + e.dataset.postid, loadPublication);

    // Se llama a /posts/postId/comments para obtener la lista de los comentarios
    $.get("/posts/" + e.dataset.postid + "/comments", loadComments);


}

document.addEventListener("click", e => {
    let pageMask = document.getElementById("page-mask");

    if (pageMask.style.display !== "none" && !e.target.closest(".modal-click-box")) 
        document.getElementById("page-mask").style.display = "none";


})