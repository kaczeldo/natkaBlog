window.onload = function () {
    let buttons = document.getElementsByClassName("services-section-button");
    let isVisible = false;

    for(let button of buttons){
        button.addEventListener('click', () => {
            const descendant = button.nextElementSibling;
            if (isVisible){
                descendant.className = "services-hidden-description";
                isVisible = false;
            } else {
                descendant.className = "services-visible-description";
                isVisible = true;
            }            
        })
    }
}