package com.example.du_an_1.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "QLG";
    private static final int DbVersion = 11;

    public DbHelper(@Nullable Context context) {
        super(context, DB_NAME,null, DbVersion);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //Tao bang Admin
        String  tb_Admin = ("CREATE TABLE Admin (" +
                "maAD TEXT PRIMARY KEY, " +
                "hoTen TEXT NOT NULL, " +
                "matKhau TEXT NOT NULL," +
                "loaiTK TEXT NOT NULL," +
                "anh TEXT," +
                "sdt TEXT," +
                "diaChi TEXT)");
        db.execSQL(tb_Admin);

        // Bang loai giay
        String tb_LoaiGiay= "CREATE TABLE LoaiGiay(" +
                "maLoai INTEGER PRIMARY KEY AUTOINCREMENT," +
                "tenLoai TEXT NOT NULL)";
        db.execSQL(tb_LoaiGiay);

        // Bang don hang
        String tb_donHang= "CREATE TABLE DonHang(" +
                "maDonHang INTEGER PRIMARY KEY AUTOINCREMENT," +
                "maAD TEXT REFERENCES Admin(maAD)," +
                "ngayDatHang TEXT NOT NULL," +
                "tongTien INTEGER NOT NULL," +
                "trangthai TEXT NOT NULL)";
        db.execSQL(tb_donHang);

        // Bang giay
        String tb_Giay= "CREATE TABLE Giay(" +
                "maGiay INTEGER PRIMARY KEY AUTOINCREMENT," +
                "tenGiay TEXT NOT NULL," +
                "giaTien INTEGER NOT NULL," +
                "maLoai INTEGER REFERENCES LoaiGiay(maLoai)," +
                "soLuong INTEGER NOT NULL," +
                "anh TEXT NOT NULL)";
        db.execSQL(tb_Giay);

        // Bảng giỏ hàng
        String tb_gioHang = "CREATE TABLE GioHang(" +
                "maGioHang integer primary key autoincrement, " +
                "maAD TEXT REFERENCES Admin(maAD)," +
                "maGiay integer REFERENCES Giay(maGiay)," +
                "soLuong integer not null)";
        db.execSQL(tb_gioHang);

//        // Bang hoa don
//        String tb_HoaDon= "CREATE TABLE HoaDon(" +
//                "maHD INTEGER PRIMARY KEY AUTOINCREMENT," +
//                "maGiay INTEGER REFERENCES Giay(maGiay)," +
//                "tenGiay TEXT REFERENCES Giay(tenGiay)," +
//                "soLuong INTEGER NOT NULL," +
//                "giaTien INTEGER NOT NULL," +
//                "ngay TEXT NOT NULL)";
//        db.execSQL(tb_HoaDon);

        //. Bảng chi tiết đơn hàng
        String chiTietDonHang = "CREATE TABLE ChiTietDonHang(" +
                "maChiTietDonHang INTEGER PRIMARY KEY AUTOINCREMENT," +
                "maDonHang INTEGER REFERENCES DonHang(maDonHang)," +
                "maGiay INTEGER REFERENCES Giay(maGiay)," +
                "soLuong INTEGER NOT NULL," +
                "donGia INTEGER NOT NULL," +
                "thanhTien INTEGER NOT NULL)";
        db.execSQL(chiTietDonHang);



        //insert date
        db.execSQL("INSERT INTO Admin VALUES ('admin1','Nguyễn Văn Admin','admin', 'admin','https://www.shareicon.net/data/128x128/2016/08/05/806962_user_512x512.png','0967110342','Hà Nội')," +
                "('admin12','Nguyễn Văn Admin','admin12', 'khachhang','https://reputationprotectiononline.com/wp-content/uploads/2022/04/78-786207_user-avatar-png-user-avatar-icon-png-transparent-287x300.png','028383','Hà Tây'), " +
                "('khachhang1','Nguyen Văn A ','123456', 'khachhang','https://www.google.com/imgres?imgurl=https%3A%2F%2Fps.w.org%2Fuser-avatar-reloaded%2Fassets%2Ficon-256x256.png%3Frev%3D2540745&tbnid=4tCxPvBodOnWbM&vet=12ahUKEwin0prGtPCCAxUvavUHHQnwDoIQMygAegQIARBT..i&imgrefurl=https%3A%2F%2Ffa.wordpress.org%2Fplugins%2Fuser-avatar-reloaded%2F&docid=7XY1G-DhNW4M4M&w=257&h=257&q=avt%20user&ved=2ahUKEwin0prGtPCCAxUvavUHHQnwDoIQMygAegQIARBT','982738489','TP Hồ Chí Minh')");

        db.execSQL("INSERT INTO LoaiGiay VALUES ('1', 'High – top')," +
                "('2', 'Mid – top')," +
                "('3', 'Low – top')");

//        db.execSQL("INSERT INTO DonHang VALUES(1, 'khachhang1', '11/01/2023', 2000, 'Đã nhận hàng')," +
//                "(2, 'khachhang1', '11/02/2023', 2000, 'Đã nhận hàng')," +
//                "(3, 'khachhang1', '11/03/2023', 2000, 'Đã nhận hàng')");

        db.execSQL("INSERT INTO Giay VALUES (1, 'Giày Hatsune Miku', 30000, 1, 12,'https://athenavshop.com/wp-content/uploads/2022/02/Giay-Hatsune-Miku-High-Top-Odyssey-Sneaker-thumb1.jpg')," +
                "(2, 'Giày B27 Mid-Top', 40000, 2,13,'https://rubystore.com.vn/wp-content/uploads/2021/07/giay-dior-b27-mid-top-sneaker-blue-gray-and-white-sieu-cap.jpg')," +
                "(3, 'Giày DSQUARED2', 54444, 3,12,'https://product.hstatic.net/1000284478/product/1062_snw0133_3_5a9a9c18eaa041f68c7ca29565dbef7c_master.jpg')," +
                "(4, 'Giày WALK’N’DIOR', 84444, 1,12,'https://shopgiayreplica.com/wp-content/uploads/2023/08/walkndior-high-top-platform-sneaker-deep-blue-dior-oblique-embroidered-cotton-avarta.jpg')," +
                "(5, 'Giày Nike Jordan', 1244844, 2,12,'https://igl.prodoc.site/images/auctions13/e7e3d47e932ca3a211a8f6da6ab58d1f-i-img900x900-1648916922sb8g8x21.jpg')," +
                "(6, 'Giày Nữ Basic', 3448444, 3,12,'https://admin.thegioigiay.com/upload/product/2023/01/giay-sneaker-nu-basic-de-banh-mi-phoi-mau-tim-size-37-63b8d09c75c5b-07012023085332.jpg')," +
                "(7, 'Giày Cổ Cao', 7844844, 1,12,'https://www.vascara.com/uploads/cms_productmedia/2023/October/31/giay-sneaker-co-cao-voi-dem-thoang-khi---snk-0069---mau-den__72268__1698769011.jpg')," +
                "(8, 'Giày Checkerboard', 794444, 2,12,'https://hanoi26sneaker.com/wp-content/uploads/2022/01/Giay-Sneaker-Vans-Checkerboard-Plaid-Tiffany-Blue-Mid-top-chat-luong-scaled.jpg')," +
                "(9, 'Giày Hatsune Miku', 804844, 3,12,'https://athenavshop.com/wp-content/uploads/2020/10/Giay-Hatsune-Miku-Low-Top-Sneaker-1.jpg')," +
                "(10, 'Giày B27 Low–top', 50000, 3, 10,'https://tungluxury.com/wp-content/uploads/2022/11/giay-dior-b27-low-top-sneaker-blue-white-6.jpg')");

//        db.execSQL("INSERT INTO HoaDon VALUES(1, 1, 'Giay bong da',  2, 1231413, '23')," +
//                "(2, 2, 'Giay',  1, 231221, '12')");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if(oldVersion != newVersion){
            db.execSQL("drop table if exists Admin");
            db.execSQL("drop table if exists HoaDon");
            db.execSQL("drop table if exists GioHang");
            db.execSQL("drop table if exists ChiTietDonHang");
            db.execSQL("drop table if exists DonHang");
            db.execSQL("drop table if exists Giay");
            db.execSQL("drop table if exists LoaiGiay");

            onCreate(db);
        }

    }
}
