import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.abledfuture.R
import com.example.abledfuture.databinding.JobTileBinding
import com.example.abledfuture.model.JobDataModel
import kotlinx.coroutines.Job

class JobHiringAdapter(private val myDataset: MutableList<JobDataModel>) :
    RecyclerView.Adapter<JobHiringAdapter.MyViewHolder>() {

    class MyViewHolder(val binding:JobTileBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(item:JobDataModel){
                binding.mainJobTitleText.text=item.job_title
            binding.jobTypeText.text=item.job_employment_type+", "+item.job_city+" "+item.job_state
            Glide.with(binding.root).load(item.employer_logo).into(binding.companyIconImageview)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding=JobTileBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
            holder.bind(myDataset[position])
    }

    override fun getItemCount() = myDataset.size
}