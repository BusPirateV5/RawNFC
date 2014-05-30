package ug.drewb.rawnfc;

import android.content.Intent;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.nfc.tech.NfcA;

import java.io.IOException;

public class NfcAActivity extends TagTechnology
{
    public void onNewIntent(Intent intent) {
        NfcA tag = NfcA.get((Tag) intent.getParcelableExtra(NfcAdapter.EXTRA_TAG));

        byte[][] commands = getCommands();
        byte[][] responses = new byte[commands.length][];

        try {
            tag.connect();
            for (int i = 0; i < commands.length; i++) {
                responses[i] = tag.transceive(commands[i]);
            }
            tag.close();
        } catch (IOException e) {
            showException(e);
        }

        showResponses(responses);
    }
}
