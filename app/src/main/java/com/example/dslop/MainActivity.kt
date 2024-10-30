package com.example.dslop
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var sinhVienAdapter: SinhVienAdapter
    private lateinit var sinhVienList: MutableList<SinhVien>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Khởi tạo danh sách sinh viên
        sinhVienList = mutableListOf(
            SinhVien("Tran Huy Hoang", "20210386"),
            SinhVien("Nguyen Thi Ha", "20190015"),
            SinhVien("Le Van Hoang", "20200021"),
            SinhVien("Pham Thi Bich", "20190016"),
            SinhVien("Bui Van Nam", "20220018"),
            SinhVien("Vu Minh Thang", "20210018"),
            SinhVien("Tran Thi Lan", "20230016"),
            SinhVien("Nguyen Van Tuan", "20220019"),
            SinhVien("Hoang Thi Hoa", "20190017"),
            SinhVien("Pham Van Khoa", "20240031"),
            SinhVien("Le Thi Hien", "20240032"),
            SinhVien("Nguyen Minh Duc", "20230017"),
            SinhVien("Tran Thi Thu", "20200022"),
            SinhVien("Pham Van An", "20190018"),
            SinhVien("Nguyen Thi Cam", "20220020"),
            SinhVien("Bui Van Hieu", "20210019"),
            SinhVien("Vu Thi Phuong", "20190019"),
            SinhVien("Nguyen Van Thanh", "20220021"),
            SinhVien("Le Thi Anh", "20230018"),
            SinhVien("Tran Van Dat", "20230019"),
            SinhVien("Nguyen Thi Tam", "20190020"),
            SinhVien("Pham Thi Lan", "20210020"),
            SinhVien("Bui Van Son", "20190021"),
            SinhVien("Le Thi Dao", "20200023"),
            SinhVien("Nguyen Minh Quan", "20210021"),
            SinhVien("Tran Thi Minh", "20200024"),
            SinhVien("Pham Van Hanh", "20240033"),
            SinhVien("Bui Minh Cuong", "20220022"),
            SinhVien("Nguyen Van Tai", "20230020"),
            SinhVien("Tran Thi Huong", "20240034"),
            SinhVien("Le Thi Tuyet", "20210022"),
            SinhVien("Nguyen Van Ha", "20200025"),
            SinhVien("Pham Van Nam", "20240035"),
            SinhVien("Bui Thi Hien", "20220023"),
            SinhVien("Le Van Hanh", "20190022"),
            SinhVien("Tran Thi Thanh", "20240036"),
            SinhVien("Nguyen Van Long", "20210023"),
            SinhVien("Pham Thi Mai", "20240037"),
            SinhVien("Bui Van Duy", "20230021"),
            SinhVien("Le Thi Hoa", "20200026"),
            SinhVien("Tran Van Kien", "20240038"),
            SinhVien("Nguyen Minh Phat", "20210024"),
            SinhVien("Pham Van Khoa", "20240039"),
            SinhVien("Bui Thi Lan", "20200027"),
            SinhVien("Le Van Thao", "20220024"),
            SinhVien("Tran Minh Quang", "20240040"),
            SinhVien("Nguyen Thi Mai", "20230022"),
            SinhVien("Pham Van Binh", "20200028"),
            SinhVien("Bui Van Tien", "20220025"),
            SinhVien("Le Van Thanh", "20240041"),
            SinhVien("Nguyen Minh Khoa", "20230023"),
            SinhVien("Tran Van Hoa", "20210025"),
            SinhVien("Pham Thi Kim", "20240042"),
            SinhVien("Bui Van Phuc", "20200029"),
            SinhVien("Le Thi Lan", "20240043"),
            SinhVien("Nguyen Van Binh", "20220026"),
            SinhVien("Tran Thi Thu", "20200030"),
            SinhVien("Pham Van Tai", "20230024"),
            SinhVien("Bui Van Tam", "20240044"),
            SinhVien("Le Thi Hoai", "20220027"),
            SinhVien("Nguyen Thi Huong", "20210026"),
            SinhVien("Tran Van An", "20230025"),
            SinhVien("Pham Thi Thao", "20220028"),
            SinhVien("Bui Van Phong", "20210027"),
            SinhVien("Le Van Kien", "20200031"),
            SinhVien("Nguyen Minh Quan", "20230026"),
            SinhVien("Tran Thi Ha", "20220029"),
            SinhVien("Pham Van Cuong", "20240045"),
            SinhVien("Bui Thi Tuyet", "20200032"),
            SinhVien("Le Thi Hanh", "20220030"),
            SinhVien("Nguyen Van Tai", "20240046"),
            SinhVien("Tran Thi Lan", "20230027"),
            SinhVien("Pham Thi Phuong", "20210028"),
            SinhVien("Bui Van Toan", "20200033"),
            SinhVien("Le Thi Thanh", "20240047"),
            SinhVien("Nguyen Van Hanh", "20230028"),
            SinhVien("Tran Thi Kim", "20200034"),
            SinhVien("Pham Van Thanh", "20220031"),
            SinhVien("Bui Thi Minh", "20210029")

        )

        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        sinhVienAdapter = SinhVienAdapter(sinhVienList)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = sinhVienAdapter

        val etSearch: EditText = findViewById(R.id.etSearch)
        etSearch.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {}

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                filter(s.toString())
            }
        })
    }

    private fun filter(text: String) {
        val filteredList = if (text.length > 2) {
            sinhVienList.filter {
                it.hoTen.contains(text, ignoreCase = true) || it.mssv.contains(text)
            }
        } else {
            sinhVienList
        }
        sinhVienAdapter.filterList(filteredList)
    }
}
