package du.ducs.messageboard.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import du.ducs.messageboard.R
import du.ducs.messageboard.model.Message
import java.text.SimpleDateFormat
import java.util.*

class MessageTileAdapter(private val messages: MutableList<Message>) :
    RecyclerView.Adapter<MessageTileAdapter.MessageViewHolder>() {

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder.
    // Each data item is just an Message object.
    class MessageViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val author: TextView = view.findViewById(R.id.author)
        val time: TextView = view.findViewById(R.id.time)
        val message: TextView = view.findViewById(R.id.message)
    }

    /**
     * Create new views (invoked by the layout manager)
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessageViewHolder {
        // create a new view
        val adapterLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.message_item, parent, false)

        return MessageViewHolder(adapterLayout)
    }

    /**
     * Replace the contents of a view (invoked by the layout manager)
     */
    override fun onBindViewHolder(holder: MessageViewHolder, position: Int) {
        val message = messages[position]
        holder.message.text = message.message
        holder.author.text = message.userId
        holder.time.text = getFormattedTime(message.timestamp)
    }

    private fun getFormattedTime(timestamp: Long): String {
        val cal = Calendar.getInstance()
        cal.timeInMillis = timestamp
        val today = Calendar.getInstance()
        // Check if the timestamp is of today and format accordingly
        return if ((cal.get(Calendar.ERA) == today.get(Calendar.ERA) &&
                    cal.get(Calendar.YEAR) == today.get(Calendar.YEAR) &&
                    cal.get(Calendar.DAY_OF_YEAR) == today.get(Calendar.DAY_OF_YEAR))
        ) {
            val formatter = SimpleDateFormat("h:mm a", Locale.ENGLISH)
            formatter.format(cal.time)
        } else {
            val formatter = SimpleDateFormat("dd/MM/yy", Locale.ENGLISH)
            formatter.format(cal.time)
        }
    }

    override fun getItemCount(): Int {
        return messages.size
    }

}
