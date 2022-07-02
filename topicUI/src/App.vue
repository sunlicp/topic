<template>
    <router-view/>
</template>

<script>
export default {
  name: 'App',
}
</script>

<style>
* {
  margin: 0;
  padding: 0;
}
::-webkit-scrollbar {
    width: 8px;
    height: 8px;
}

::-webkit-scrollbar-track {
    background-color: rgba(73, 177, 245, 0.2);
    border-radius: 2em;
}

::-webkit-scrollbar-thumb {
    background-color: #49b1f5;
    background-image: -webkit-linear-gradient(
            45deg,
            rgba(255, 255, 255, 0.4) 25%,
            transparent 25%,
            transparent 50%,
            rgba(255, 255, 255, 0.4) 50%,
            rgba(255, 255, 255, 0.4) 75%,
            transparent 75%,
            transparent
    );
    border-radius: 2em;
}

::-webkit-scrollbar-corner {
    background-color: transparent;
}

::-moz-selection {
    color: #fff;
    background-color: #49b1f5;
}

body {
  cursor: url("/static/md-cursors/pointer.cur"), auto;
}
a img{
  cursor: url("/static/md-cursors/link.cur"), auto;
}
a:hover {
  cursor: url("/static/md-cursors/link.cur"), auto;
}
.hty-icon-button {
  cursor: url("/static/md-cursors/link.cur"), pointer;
}
input {
  cursor: url("/static/md-cursors/text.cur"), auto;
}

</style>
