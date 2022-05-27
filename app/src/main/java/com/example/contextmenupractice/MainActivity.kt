package com.example.contextmenupractice

import android.os.Bundle
import android.view.*
import android.widget.AdapterView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.contextmenupractice.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var actionMode: ActionMode? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        // context menu 등록하기
        registerForContextMenu(binding.button)
    }

    override fun onCreateContextMenu(menu: ContextMenu?, v: View?, menuInfo: ContextMenu.ContextMenuInfo?) {
        super.onCreateContextMenu(menu, v, menuInfo)
        menuInflater.inflate(R.menu.bottom_navigation_menu, menu)
    }

    // 메뉴 아이템 선택을 성공적으로 핸들링하면 return true
    // 성공적으로 하지 못하면 super 호출
    // 만약에 액티비티가 프래그먼트를 포함하고 있으면, 액티비티가 가장먼저 콜백을 받음
    // super 가 호출되었으면 디벤트를 각 프레그먼트에 pass (차례로 ture or false 를 호출할때까지..)
    // activity 와 fragment 는 default 로 false 를 리턴하기 때문에 unhandled 되었을 때 항상 super 를 불러줘야한다,,
    override fun onContextItemSelected(item: MenuItem): Boolean {
//        val info = item.menuInfo as AdapterView.AdapterContextMenuInfo
        return when (item.itemId) {
            R.id.navigation_menu_main -> {
                Toast.makeText(this, "홈", Toast.LENGTH_SHORT).show()
                true
            }
            else -> {
                super.onContextItemSelected(item)
            }
        }
    }

    /*
    // contextual menu 사용하기 (앱바영역에 가로로 길에 뜸)
    // Action mode (long pressed 등) 에서 구현하기
    // Action mode 에서 contextual action bar 가 사라지는건 - 모든 아이템을 선택하지 않거나, 백버튼을 눌렀거나, 완료버튼을 눌렀을 때.
    private val actionModeCallback = object : ActionMode.Callback {
        override fun onCreateActionMode(mode: ActionMode, menu: Menu): Boolean {
            // inflate a menu resource providing context menu items
            val inflater: MenuInflater = mode.menuInflater
            inflater.inflate(R.menu.bottom_navigation_menu, menu)
            return true
        }

        // called each time the action mode is shown.
        // always called after onCreateActionMode,
        // but may be called multiple times if the mode invalidated
        override fun onPrepareActionMode(mode: ActionMode, menu: Menu): Boolean {
            return false // Return false if nothing is done
        }

        // called when the user selects a contextual menu item
        // 이벤트콜백을 각 액션모드에도 전달
        override fun onActionItemClicked(mode: ActionMode, item: MenuItem): Boolean {
            return when (item.itemId){
                R.id.home -> {
                    // do
                    mode.finish()   // action picked, so close the CAB
                    true
                }
                else -> {
                    false
                }
            }
        }

        override fun onDestroyActionMode(mode: ActionMode) {
            actionMode = null
        }
    }

    private fun setListeners(){
        binding.button.setOnLongClickListener { view ->
            when(actionMode) {
                null -> {
                    actionMode = this.startActionMode(actionModeCallback)
                    view.isSelected = true
                    true
                } else -> {
                    false
                }
            }
        }
    }
     */
}