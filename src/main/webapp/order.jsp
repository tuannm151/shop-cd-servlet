<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <link
      rel="stylesheet"
      href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.8.1/font/bootstrap-icons.css"
    />
    <link rel="preconnect" href="https://fonts.googleapis.com" />
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin />
    <link
      href="https://fonts.googleapis.com/css2?family=Sora:wght@100;200;300;400;500;600;700&display=swap"
      rel="stylesheet"
    />

    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/global.css" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/normalize.css" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/header.css" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/order.css" />

    <script defer>
      const ctx = "${pageContext.request.contextPath}";
    </script>
    <script defer src="js/sticky.js"></script>
    <title>Orders</title>
  </head>
  <body>
    <header class="header" id="header">
      <div class="header-left">
        <div class="logo">
          <i class="bi bi-bootstrap icon"></i>
        </div>
        <span class="user-email">/ ${sessionScope.user.email}</span>
      </div>

      <ul class="nav">
        <li class="nav__item">
          <a href="${pageContext.request.contextPath}/" class="nav__link"
            >Home</a
          >
        </li>
        <li class="nav__item">
          <a href="${pageContext.request.contextPath}/cart" class="nav__link"
            >Cart</a
          >
        </li>
        <li class="nav__item">
          <a href="${pageContext.request.contextPath}/orders" class="nav__link">My Orders</a>
        </li>
        <li class="nav__item">
          <a href="${pageContext.request.contextPath}/logout" class="nav__link">Logout</a>
        </li>
      </ul>
      <div class="cart-logo">
        <i class="bi bi-cart-check cart__icon"></i>
        <span
          class="cart__count"
          id="cartCount"
          data-cart-count="${cart.totalSize}"
          >0</span
        >
      </div>
    </header>
    <section class="order-section">
      <div class="order-container">
        <div class="order__content">
          <h2 class="order__title">My Orders</h2>
          <div class="order__header">
            <span class="order__header-item">Number</span>
            <span class="order__header-item">Order</span>
            <span class="order__header-item">Date</span>
            <span class="order__header-item">Total</span>
            <span class="order__header-item">Status</span>
          </div>
          <ul class="order__items">
            <li class="order__item active">
              <span class="order__item-info">OD001</span>
              <span class="order__item-info">3 items</span>
              <span class="order__item-info">15/03/2022</span>
              <span class="order__item-info">$396.44</span>
              <span class="order__item-info">Pending</span>
              <button class="order__item-action">
                <i class="bi bi-eye"></i> See detail
              </button>
            </li>
            <li class="order__item">
              <span class="order__item-info">OD001</span>
              <span class="order__item-info">3 items</span>
              <span class="order__item-info">15/03/2022</span>
              <span class="order__item-info">$396.44</span>
              <span class="order__item-info">Pending</span>
              <button class="order__item-action">
                <i class="bi bi-eye"></i> See detail
              </button>
            </li>
            <li class="order__item">
              <span class="order__item-info">OD001</span>
              <span class="order__item-info">3 items</span>
              <span class="order__item-info">15/03/2022</span>
              <span class="order__item-info">$396.44</span>
              <span class="order__item-info">Pending</span>
              <button class="order__item-action">
                <i class="bi bi-eye"></i> See detail
              </button>
            </li>
          </ul>
        </div>
        <div class="order__detail">
          <h3 class="order__detail-title">Order Summary</h3>
          <div class="order__detail-content">
            <div class="order__detail-item">
              <div class="order__detail-img">
                <img src="https://via.placeholder.com/150" alt="" />
              </div>
              <div class="order__detail-info">
                <div class="order__detail-name">
                  <span>CD 1</span>
                </div>
                <div class="order__detail-price">
                  <span>$396.44</span>
                </div>
                <div class="order__detail-quantity">
                  <span>Quantity: 1</span>
                </div>
              </div>
            </div>
            <div class="order__detail-item">
              <div class="order__detail-img">
                <img src="https://via.placeholder.com/150" alt="" />
              </div>
              <div class="order__detail-info">
                <div class="order__detail-name">
                  <span>CD 1</span>
                </div>
                <div class="order__detail-price">
                  <span>$396.44</span>
                </div>
                <div class="order__detail-quantity">
                  <span>Quantity: 1</span>
                </div>
              </div>
            </div>
          </div>
          <div class="order__detail-contact">
            <h3 class="order__detail-title order__detail-title--contact">
              Shipping
            </h3>
            <div class="order__detail-contact-item">
              <i class="bi bi-geo-alt"></i>
              <span class="order__detail-contact-text"
                >Dai An - Ha Dong - Ha Noi
              </span>
            </div>
            <div class="order__detail-contact-item">
              <i class="bi bi-telephone"></i>
              <span class="order__detail-contact-text">0987654321</span>
            </div>
          </div>
          <div class="checkout__cart-info">
            <span class="checkout__cart-info-item-label"
              >Subtotal (<span id="checkout-cart-count">5}</span>)</span
            >
            <span class="checkout__cart-info-item-value">$395.44</span>
            <span class="checkout__cart-info-item-label">Shipping</span>
            <span class="checkout__cart-info-item-value">$0</span>
            <span class="checkout__cart-info-item-label item-text--total"
              >Total price</span
            >
            <span class="checkout__cart-info-item-value item-text--total"
              >$395.44</span
            >
          </div>
        </div>
      </div>
    </section>
  </body>
</html>
