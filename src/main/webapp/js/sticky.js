const header = document.getElementById("header");

const sticky = header.offsetTop;

window.onscroll = function () {
  changeHeaderStatus();
};

function changeHeaderStatus() {
  if (window.pageYOffset > sticky) {
    header.classList.add("sticky");
  } else {
    header.classList.remove("sticky");
  }
}
