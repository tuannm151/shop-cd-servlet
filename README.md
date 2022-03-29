# shop-cd-servlet
A website selling CD music albums. 
-	Tech: HTML, CSS, JS, Java Servlet, JSP
-	Mô tả: Thiết kế website bán album đĩa CD
-	Live web: https://cd-shop-servlet.herokuapp.com
-	Chi tiết:
*  Ngày 7-9/3 thiết kế giao diện đăng kí và đăng nhập bằng html, css. Thực hiện client-side validate bằng JS
	 *  Tạo webserver bằng java servlet để handle dữ liệu từ form
	 *  Sử dụng JDBC để thao tác với PostgreSQL
	 *  Các controller servlet đóng vai trò điều khiển khi nhận các request get, post từ form  	
 	 *  UserDAO là class chứa các method thao tác với cơ sở dữ liệu (insert user, check login, check mail exists)
 	 *  Class Auth bao gồm các method hỗ trợ server-validate input từ form đăng nhập đăng kí
 	 *  Sử dụng JSP để render ra các attribute sau khi đã xử lý và set ở servlet

*  Ngày 10/3 thiết kế giao diện cho home page, tạo card list các sản phẩm
 	  *  Tạo thêm một HomePageServlet controller cho home page, và ProductDAO để đọc dữ liệu sản phẩm từ database.
  
-  12/3: Thiết kế giao diện giỏ hàng và tạo thêm một CartServlet, CartDTO để đọc dữ liệu từ db
 
*  13/3, thực hiện xử lí việc thao tác với các sản phẩm của giỏ hàng (thêm sửa xoá) bằng js thông qua bắt sự kiện DOM
		*  đồng thời fetch dữ liệu về phía servlet để đồng bộ với server và csdl.
*  15/3, thiết kế và hoàn thiện chức năng xem trạng thái các đơn hàng đã đặt.

*	 Chức năng dự kiến sẽ thêm: 
		*  Phân quyền người dùng
		*  Trang thêm sửa xoá sản phẩm cho admin
    
