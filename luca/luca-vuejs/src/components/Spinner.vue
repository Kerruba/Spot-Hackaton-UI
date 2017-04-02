<template>
  <div class="loader"></div>
</template>

<style lang="scss">

  $innerSize: 3em;
  $outerSize: 0.5em;
  $opacity: 0.5;
  $color: #007c82;

  .loader,
  .loader:after {
    border-radius: 50%;
    width: $innerSize;
    height: $innerSize;
  }
  .loader {
    margin: $innerSize auto;
    font-size: 10px;
    position: relative;
    text-indent: -9999em;
    border-top: $outerSize solid rgba(255, 255, 255, $opacity);
    border-right: $outerSize solid rgba(255, 255, 255, 0.5);
    border-bottom: $outerSize solid rgba(255, 255, 255, 0.5);
    border-left: $outerSize solid #ffffff;
    -webkit-transform: translateZ(0);
    -ms-transform: translateZ(0);
    transform: translateZ(0);
    -webkit-animation: load8 1.1s infinite linear;
    animation: load8 1.1s infinite linear;
    background: $color;
  }
  @-webkit-keyframes load8 {
    0% {
      -webkit-transform: rotate(0deg);
      transform: rotate(0deg);
    }
    100% {
      -webkit-transform: rotate(360deg);
      transform: rotate(360deg);
    }
  }
  @keyframes load8 {
    0% {
      -webkit-transform: rotate(0deg);
      transform: rotate(0deg);
    }
    100% {
      -webkit-transform: rotate(360deg);
      transform: rotate(360deg);
    }
  }
</style>
