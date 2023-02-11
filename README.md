## Quản lý tổ dân phố
Phần mềm **Sorting Algorithms Visualisation** _v0.0.1_ được phát triển bởi 5 sinh viên của Đại học Bách khoa Hà Nội. Chúng tôi gồm có:
- _Phạm Huy Hà Thái - Developer (Lead) - IT2 K65_
- _Hà Hiểu Thành - Developer - IT2 K65_
- _Nguyễn Xuân Bách - Developer - IT2 K65_
- _Bùi Trần Phương - Developer - IT2 K65_
- _Hồ Sỹ Vinh - Developer - IT2 K65_

**Quản lý tổ dân phố** ra đời với mục đích hỗ trợ việc quản lý các thông tin liên quan tới một khu vực, có thể mở rộng với quy mô lớn hơn. Với chức năng đầy đủ, giao diện thân thiện, trực quan cùng các bước thao tác đơn giản, chúng tôi hi vọng **Quản lý tổ dân phố** có thể hỗ trợ tối đa cho người dùng trong việc quản lý khu dân cư.

Đây là dự án mã nguồn mở dưới sự cấp phép của [MIT License](https://opensource.org/licenses/MIT), nên việc đóng góp và phát triển sản phẩm đều rất khuyến khích dưới các yêu cầu và quy định của giấy phép để đem trải nghiệm sản phẩm tốt hơn. 

Mọi đóng góp và thắc mắc xin liên hệ:
- _Email: thai.phh204784@sis.hust.edu.vn_
- _Điện thoại: 0904951358_

## Ngôn ngữ
Phần mềm được lập trình bằng ngôn ngữ ***Java***, xây dựng giao diện với ***JavaFX***, ngôn ngữ ***XML*** và ***CSS***. Một số tính năng khác có yêu cầu các thư viện từ **[GluonHQ](https://gluonhq.com/)** để mang trải nghiệm người dùng tốt hơn. Vì vậy việc cài đặt các thư viện này là cần thiết để phần mềm có thể chạy đúng tính năng của nó.

Chức năng chính của phần mềm được phát triển bằng ***[InteliJ IDEA](https://www.jetbrains.com/idea/download/)*** và giao diện được xây dựng bằng ***[JavaFX](https://gluonhq.com/products/javafx/)***.

## Cài đặt
Dưới đây là các bước cài đặt cơ bản của **Quản lý tổ dân phố**:

### Cài đặt IDE (InteliJ IDEA)
Tải về và cài đặt theo hướng dẫn tại trang chủ của [InteliJ IDEA](https://www.jetbrains.com/idea/download/).

### Cài đặt JavaFX và thiết lập trong IDE
- Tải và cài đặt **JavaFX** tại [GluonHQ](https://gluonhq.com/products/javafx/).
- Thêm ***Environment Variables***:
  - **Windows**: ```set PATH_TO_FX="path\to\javafx-sdk-15.0.1\lib"```
  - **MacOS/Linux**: ```export PATH_TO_FX=path/to/javafx-sdk-15.0.1/lib```

    Để JavaFX tự động chạy khi khởi động Linux, hãy thêm câu lệnh trên vào ```~/.bashrc```.
  - Thêm vào **configuration**:
    
    Mở IDE (Inntellij), vào **Run**, chọn **Edit Configuration**, chọn **Add VM variables** và thêm:
      
      ```--module-path $PATH_TO_FX --add-modules javafx.controls,javafx.fxml``` 

## Tính năng chính
### - Quản lý nhân khẩu
### - Quản lý sổ hộ khẩu
### - Quản lý cơ sở vật chất
### - Quản lý lịch hoạt động
Đối với mỗi nhóm chức năng, chúng tôi đều cung cấp các thao tác thêm, sửa, xóa, tìm kiếm giúp người dùng dễ dàng thao tác và quản lý dữ liệu tổ dân phố của mình.

## Về dự án
Đây là một dự án mã nguồn mở cấp phép dưới ***MIT License***, với mục đích giáo dục. Mọi hành vi thương mại hóa, close-source đều bị nghiêm cấms.
