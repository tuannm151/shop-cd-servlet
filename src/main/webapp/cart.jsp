<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/cart.css" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" />

    <script defer>const ctx = "${pageContext.request.contextPath}"</script>
    <script defer src="js/sticky.js"></script>
    <script defer src="js/cart.js"></script>
    <title>Cart</title>
  </head>
  <body>
    <header class="header" id="header">
      <div class="header-left">
        <div class="logo">
          <i class="bi bi-bootstrap icon"></i>
        </div>
        <span class="user-email">/ ${sessionScope.user.email} </span>
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
        <span class="cart__count" id="cartCount" data-cart-count="${cart.totalSize}">${cart.totalSize}</span>
      </div>
    </header>
    <section class="cart-section">
      <div class="cart-container">
        <div class="cart__header">
          <div class="cart__header-title">Product</div>
          <div class="cart__header-title">Price</div>
          <div class="cart__header-title">Qty</div>
          <div class="cart__header-title">Total</div>
          <div class="cart__header-action"></div>
        </div>
        <c:choose>
          <c:when test="${cartCount == 0}">
            <div class="error-message">
              <h2>Cart is empty</h2>
            </div>
          </c:when>
            <c:when test="${error != null}">
              <div class="error-message">
                <h2>${error}</h2>
              </div>
            </c:when>
          <c:otherwise>
            <ul class="cart__items">
              <c:forEach var="cartItem" items="${cart.cartItems}">
                <li class="cart__item">
                  <div class="cart__item-content">
                    <div class="cart__item-img">
                      <img
                              src="${cartItem.product.imageUrl}"
                              alt="A Music CD"
                      />
                    </div>
                    <div class="cart__item-info">
                      <h3 class="cart__item-title">${cartItem.product.name}</h3>
                      <p class="cart__item-description">${cartItem.product.description}</p>
                    </div>
                  </div>
                  <div class="cart__item-price">
                <span class="cart__item-price-value">$<fmt:formatNumber
                        value="${cartItem.product.price}" minFractionDigits="2"/></span>
                  </div>
                  <div class="cart__item-qty" data-product-id="${cartItem.product.id}" data-product-price="${cartItem.product.price}" data-qty-value="${cartItem.quantity}">
                    <button class="btn-action cart__item-qty-btn" data-action="reduce">
                      <i class="bi bi-dash-lg"></i>
                    </button>
                    <span class="cart__item-qty-value">${cartItem.quantity}</span>
                    <button class="btn-action cart__item-qty-btn" data-action="add">
                      <i class="bi bi-plus-lg"></i>
                    </button>
                  </div>
                  <div class="cart__item-total">
                <span class="cart__item-total-value">$<fmt:formatNumber
                        value="${cartItem.getTotalPrice()}" minFractionDigits="2"/></span>
                  </div>
                  <button class="btn-action cart__item-remove" data-action="delete">
                    <i class="bi bi-x-lg"></i>
                  </button>
                </li>

              </c:forEach>

            </ul>
          </c:otherwise>
        </c:choose>


        <div class="cart-info"></div>
      </div>
    </section>
    <form method="post" action="${pageContext.request.contextPath}/orders" class="checkout-container">
      <div class="checkout__account-info">
        <h4 class="checkout__header">Account</h4>
        <span class="checkout__account-info-text">${sessionScope.user.name}</span>
        <span class="checkout__account-info-text">${sessionScope.user.email}</span>
      </div>
      <div class="checkout__shipping-info">
        <h4 class="checkout__header">Shipping</h4>
        <div class="checkout__fields-group">
          <div class="field-group">
            <input
              type="text"
              name="address"
              id="address"
              class="field-group__input"
              required
            />
            <label for="address" class="field-group__label">Address</label>
            <span class="field-group__error"></span>
          </div>
          <div class="field-group">
            <input
              type="text"
              name="phone"
              id="phone"
              class="field-group__input"
              required
            />
            <label for="phone" class="field-group__label">Phone</label>
            <span class="field-group__error"></span>
          </div>
        </div>
      </div>

      <div class="checkout__cart-info">
        <h4 class="checkout__header">Order Summary</h4>
        <div class="checkout__cart-info-item">
          <span class="checkout__cart-info-item-label">Subtotal (<span id="checkout-cart-count">${cart.totalSize}</span>)</span>
          <span class="checkout__cart-info-item-value" data-subtotal="${cart.totalPrice}" id="subtotal">$<fmt:formatNumber
                  value="${cart.totalPrice}" minFractionDigits="2"/></span>
          <span class="checkout__cart-info-item-label">Shipping</span>
          <span class="checkout__cart-info-item-value">$0</span>
          <span class="checkout__cart-info-item-label">Total</span>
          <span class="checkout__cart-info-item-value" id="total">$<fmt:formatNumber
                  value="${cart.totalPrice}" minFractionDigits="2"/></span>
        </div>
        <button type="submit" class="checkout__cart-btn">Place Order <span id="total-value">$<fmt:formatNumber
                value="${cart.totalPrice}" minFractionDigits="2"/></span></button>
      </div>
    </form>
  </body>
</html>
